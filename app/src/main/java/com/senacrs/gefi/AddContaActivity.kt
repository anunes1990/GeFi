package com.senacrs.gefi

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.senacrs.gefi.model.Compra

class AddContaActivity : AppCompatActivity() {

    private var idCartao: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_conta)
        idCartao = intent.getStringExtra("idCartao")
    }

    fun salvar(view: View?){
        val txtDesc = findViewById<EditText>(R.id.txtDescricao)
        val txtValor = findViewById<EditText>(R.id.txtValor)
        val txtVezes = findViewById<EditText>(R.id.txtVezes)
        val txtParcela = findViewById<EditText>(R.id.txtParcela)

        val id = idCartao.toString()
        val descricao = txtDesc.text.toString()
        val valor = txtValor.text.toString()
        val vezes = txtVezes.text.toString()
        val parcela = txtParcela.text.toString()

        val compra = Compra(id.toInt(),descricao, valor.toDouble(), vezes.toInt(), parcela.toInt())

        val it = Intent().apply {
            putExtra("compra", compra)
        }

        setResult(Activity.RESULT_OK, it)
        finish()
    }

    fun cancelar(view: View?){
        finish()
    }

}
