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
import com.senacrs.gefi.daos.CompraDao
import com.senacrs.gefi.database.AppDatabase
import com.senacrs.gefi.model.Compra

class AddCompraActivity : AppCompatActivity() {


    private var idCartao: String? = ""
    var db: AppDatabase? = null
    var cartaoDao: CartaoDao? = null
    var compraDao: CompraDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_compra)
        idCartao = intent.getStringExtra("idCartao")

        db = Room.databaseBuilder(this, AppDatabase::class.java, "myDB")
            .allowMainThreadQueries().build()
        cartaoDao = db?.cartaoDao()
        compraDao = db?.compraDao()
    }

    fun salvar(view: View?){
        val txtDesc = findViewById<EditText>(R.id.txtDescricao)
        val txtValor = findViewById<EditText>(R.id.txtValor)
        val txtVezes = findViewById<EditText>(R.id.txtVezes)
        val txtParcela = findViewById<EditText>(R.id.txtParcela)

        val idCartao = idCartao.toString()
        val descricao = txtDesc.text.toString()
        val valor = txtValor.text.toString()
        val vezes = txtVezes.text.toString()
        val parcela = txtParcela.text.toString()

        val compra = Compra(0, idCartao.toLong(),descricao, valor.toDouble(), vezes.toInt(), parcela.toInt())

        val id = compraDao?.insertCompra(compra)
        val retorno = compraDao?.compraById(id!!)
        Log.d("[add_compra]", "${retorno?.id} | ${retorno?.idCartao} | ${retorno?.valor} | ${retorno?.vezes} | ${retorno?.parcelaAtual}")


        var cartao = cartaoDao?.cartoesById(retorno?.idCartao!!)
        cartao?.valorGasto = cartao?.valorGasto!! + retorno?.valor!!
        cartaoDao?.updateCartao(cartao)
        val retornoCartao = cartaoDao?.cartoesById(cartao.id!!)
        Log.d("[update_cartao]", "${retornoCartao?.id} | ${retornoCartao?.nome} | ${retornoCartao?.limite} | ${retornoCartao?.diaVencimento} | ${retornoCartao?.valorGasto}")

        val it = Intent().apply {
            putExtra("compra", retorno)
        }

        setResult(Activity.RESULT_OK, it)
        finish()
    }

    fun cancelar(view: View?){
        finish()
    }

}
