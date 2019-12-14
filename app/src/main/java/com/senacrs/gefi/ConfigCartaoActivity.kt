package com.senacrs.gefi

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import com.senacrs.gefi.daos.CartaoDao
import com.senacrs.gefi.daos.CompraDao
import com.senacrs.gefi.database.AppDatabase
import com.senacrs.gefi.model.Cartao

class ConfigCartaoActivity : AppCompatActivity() {
    private var idCartao : String? = ""
    private var cartao: Cartao? = null
    var db: AppDatabase? = null
    var dao: CartaoDao? = null
    var daoCompras: CompraDao? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_cartao)
        supportActionBar!!.title = "Config Cartão"

        db = Room.databaseBuilder(this, AppDatabase::class.java, "myDB")
            .allowMainThreadQueries().build()
        dao = db?.cartaoDao()
        daoCompras = db?.compraDao()

        idCartao = intent.getStringExtra("idCartao")
        val id = idCartao.toString()
        cartao = dao?.cartoesById(id.toLong())

        val nome = findViewById<EditText>(R.id.txtNome)
        nome.setText(cartao?.nome)

        val limite = findViewById<EditText>(R.id.txtLimite)
        limite.setText(cartao?.limite?.toString())

        val vencimento = findViewById<EditText>(R.id.txtVencimento)
        vencimento.setText(cartao?.diaVencimento?.toString())
    }

    fun excluir(view: View?){
        val compras = daoCompras?.comprasCartaoFindAll(cartao?.id!!)

        if(compras.isNullOrEmpty()){
            dao?.deleteCartao(cartao as Cartao)
            val it = Intent().apply {
                putExtra("cartao", cartao)
            }
            setResult(666, it)
            finish()
        } else {
            Toast.makeText(this, "O cartão possui compras associadas!", Toast.LENGTH_SHORT).show()
        }

    }

    fun voltar(view: View?){
        finish()
    }

    fun salvar(view: View?){

        val txtNome = findViewById<EditText>(R.id.txtNome).text.toString()
        cartao?.nome = txtNome
        val txtLimite = findViewById<EditText>(R.id.txtLimite).text.toString()
        cartao?.limite = txtLimite.toDouble()
        val txtVencimento = findViewById<EditText>(R.id.txtVencimento).text.toString()
        cartao?.diaVencimento = txtVencimento.toInt()

        cartao?.limiteDisponivel = cartao?.limite!! - cartao?.valorGasto!!

        dao?.updateCartao(cartao as Cartao)

        val it = Intent().apply {
            putExtra("cartao", cartao)
        }

        setResult(Activity.RESULT_OK, it)
        finish()
    }
}
