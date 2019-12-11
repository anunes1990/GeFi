package com.senacrs.gefi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun goContas(view: View){
        var intent = Intent(this, ContasActivity::class.java).apply {
            putExtra("msg", "Navegação Contas Bombando")
        }
        startActivityForResult(intent, 1)
    }

    fun goCartoes(view: View){
        var intent = Intent(this, CartoesActivity::class.java).apply {
            putExtra("msg", "Navegação Cartoes Bombando")
        }
        startActivityForResult(intent, 1)
    }



}
