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
import com.senacrs.gefi.model.Cartao
import com.senacrs.gefi.model.Compra

class ConfigCompraActivity : AppCompatActivity() {
    private var idCartao : String? = ""
    private var idCompra : String? = ""
    private var cartao: Cartao? = null
    private var compra: Compra? = null

    var db: AppDatabase? = null
    var daoCartao: CartaoDao? = null
    var daoCompra: CompraDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_compra)
        supportActionBar!!.title = "Config Compra"

        db = Room.databaseBuilder(this, AppDatabase::class.java, "myDB")
            .allowMainThreadQueries().build()
        daoCartao = db?.cartaoDao()
        daoCompra = db?.compraDao()

        idCartao = intent.getStringExtra("idCartao")
        val idCartao = idCartao.toString()

        idCompra= intent.getStringExtra("idCompra")
        val idCompra = idCompra.toString()

        cartao = daoCartao?.cartoesById(idCartao.toLong())
        compra = daoCompra?.compraById(idCompra.toLong())

        val descricao = findViewById<EditText>(R.id.txtDescricao)
        descricao.setText(compra?.descricao)

        val valor = findViewById<EditText>(R.id.txtValor)
        valor.setText(compra?.valor?.toString())

       val vezes = findViewById<EditText>(R.id.txtVezes)
        vezes.setText(compra?.vezes?.toString())

        val parcela = findViewById<EditText>(R.id.txtParcela)
        parcela.setText(compra?.parcelaAtual?.toString())
    }

    fun excluir(view: View?){
        val saldoAntigo = cartao?.valorGasto
        val debitoAntigo = compra?.valor
        var novoSaldo = saldoAntigo!! - debitoAntigo!!

        cartao?.valorGasto = novoSaldo

        daoCompra?.deleteCompra(compra as Compra)
        daoCartao?.updateCartao(cartao as Cartao)

        val it = Intent().apply {
            putExtra("compra", compra)
        }

        setResult(666, it)
        finish()

        finish()
    }

    fun voltar(view: View?){
        finish()
    }

    fun salvar(view: View?){

        val saldoAntigo = cartao?.valorGasto
        val debitoAntigo = compra?.valor
        var novoSaldo = saldoAntigo!! - debitoAntigo!!

        val txtDescricao = findViewById<EditText>(R.id.txtDescricao).text.toString()
        compra?.descricao = txtDescricao

        val txtValor = findViewById<EditText>(R.id.txtValor).text.toString()
        compra?.valor = txtValor.toDouble()
        cartao?.valorGasto = novoSaldo + compra?.valor!!

        val txtVezes = findViewById<EditText>(R.id.txtVezes).text.toString()
        compra?.vezes = txtVezes.toInt()

        val txtParcela = findViewById<EditText>(R.id.txtParcela).text.toString()
        compra?.parcelaAtual = txtParcela.toInt()

        daoCompra?.updateCompra(compra as Compra)
        daoCartao?.updateCartao(cartao as Cartao)

        val it = Intent().apply {
            putExtra("compra", compra)
        }

        setResult(Activity.RESULT_OK, it)
        finish()
    }
}
