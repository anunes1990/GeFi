package com.senacrs.gefi

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.senacrs.gefi.model.Cartao
import kotlin.random.Random

class AddCartaoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cartao)
    }

    fun cancelar(view: View?){
        finish()
    }

    fun salvar(view: View?){
        val txtNome = findViewById<EditText>(R.id.txtNome)
        val txtLimite = findViewById<EditText>(R.id.txtLimite)
        val txtVencimento = findViewById<EditText>(R.id.txtVencimento)

        val id = Random.nextInt(5,100)
        val nome = txtNome.text.toString()
        val limite = txtLimite.text.toString()
        val vencimento = txtVencimento.text.toString()

        val cartao = Cartao(id, nome, limite.toDouble(), vencimento.toInt())

        val it = Intent().apply {
            putExtra("cartao", cartao)
        }

        setResult(Activity.RESULT_OK, it)
        finish()
    }
}
