package com.senacrs.gefi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title = "GeFi - Gestão Financeira"
    }

    fun goContas(view: View){
        var intent = Intent(this, ComprasCartaoActivity::class.java)
        startActivityForResult(intent, 1)
    }

    fun goCartoes(view: View){
        var intent = Intent(this, CartoesActivity::class.java)
        startActivityForResult(intent, 1)
    }



}
