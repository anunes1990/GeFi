package com.senacrs.gefi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.room.Room
import com.senacrs.gefi.daos.CartaoDao
import com.senacrs.gefi.database.AppDatabase
import com.senacrs.gefi.model.Cartao

class MainActivity : AppCompatActivity() {
    var db: AppDatabase? = null
    var dao: CartaoDao? = null
    var cartoes: List<Cartao>? = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title = "GeFi - Gestão Financeira"

        db = Room.databaseBuilder(this, AppDatabase::class.java, "myDB")
            .allowMainThreadQueries().build()
        dao = db?.cartaoDao()

        cartoes = dao?.cartoesFindAll()

        val txtSaldo = findViewById<TextView>(R.id.txtSaldo)
        val txtTotalMes = findViewById<TextView>(R.id.txtTotalMes)

        if (cartoes.isNullOrEmpty()){
            txtSaldo.setText("R$ 00.0")
            txtTotalMes.setText("R$ 00.0")
        } else {
            val totalGasto = dao?.getTotalGasto()
            txtSaldo.setText("R$ ${totalGasto}")
            val totalMes = dao?.getTotalMes()
            txtTotalMes.setText("R$ ${totalMes}")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)

        val txtSaldo = findViewById<TextView>(R.id.txtSaldo)
        val txtTotalMes = findViewById<TextView>(R.id.txtTotalMes)

        if (cartoes.isNullOrEmpty()){
            txtSaldo.setText("R$ 00.0")
            txtTotalMes.setText("R$ 00.0")
        } else {
            val totalGasto = dao?.getTotalGasto()
            txtSaldo.setText("R$ ${totalGasto}")
            val totalMes = dao?.getTotalMes()
            txtTotalMes.setText("R$ ${totalMes}")
        }
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
