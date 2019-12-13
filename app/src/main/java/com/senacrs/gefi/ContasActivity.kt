package com.senacrs.gefi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.senacrs.gefi.adapter.CartoesAdapter
import com.senacrs.gefi.adapter.ContasAdapter
import com.senacrs.gefi.model.Cartao
import com.senacrs.gefi.model.Conta

class ContasActivity : AppCompatActivity() {
    private var listaContas: ArrayList<Conta> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contas)

        //val msg = intent.getStringExtra("msg")
        //val texto = findViewById<TextView>(R.id.txtMsg)
        //texto.text = msg

        viewManager = LinearLayoutManager(this)
        viewAdapter = ContasAdapter(listaContas)

        listaContas.add(Conta(1,"América Express",15, 3000.00))
        listaContas.add(Conta(2,"NU Bank",8,3250.00))
        listaContas.add(Conta(3,"Credicard ZERO",12,3500.00))
        listaContas.add(Conta(4,"Magazine Luiza",5,3750.00))

        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewContas).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    fun goExplode(view:View){
        var intent = Intent(this, ContaExplodeActivity::class.java).apply {
            putExtra("idCartao", view.findViewById<TextView>(R.id.txtIdCartao).getText().toString())
        }
        startActivityForResult(intent, 1)
    }


}
