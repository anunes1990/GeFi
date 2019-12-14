package com.senacrs.gefi

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.room.Room
import com.senacrs.gefi.daos.CartaoDao
import com.senacrs.gefi.database.AppDatabase
import com.senacrs.gefi.model.Cartao

class AddCartaoActivity : AppCompatActivity() {

    var db:AppDatabase? = null
    var dao: CartaoDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cartao)
        supportActionBar!!.title = "Cadastro de Cartão"

        db = Room.databaseBuilder(this, AppDatabase::class.java, "myDB")
            .allowMainThreadQueries().build()
        dao = db?.cartaoDao()
    }

    fun cancelar(view: View){
        finish()
    }

    fun salvar(view: View){
        val txtNome = findViewById<EditText>(R.id.txtNome)
        val txtLimite = findViewById<EditText>(R.id.txtLimite)
        val txtVencimento = findViewById<EditText>(R.id.txtVencimento)

        val nome = txtNome.text.toString()
        val limite = txtLimite.text.toString()
        val vencimento = txtVencimento.text.toString()

        val cartao = Cartao(0, nome, limite.toDouble(),limite.toDouble(), vencimento.toInt(), 0.0, 0.0)
        val id = dao?.insertCartao(cartao)
        val retorno = dao?.cartoesById(id!!)

        val it = Intent().apply {
            putExtra("cartao", retorno)
        }

        setResult(Activity.RESULT_OK, it)
        finish()
    }
}
