package com.senacrs.gefi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.senacrs.gefi.adapter.CartoesAdapter
import com.senacrs.gefi.model.Cartao

class CartoesActivity : AppCompatActivity() {
    private var listaCartoes: ArrayList<Cartao> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartoes)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Cartões"

        //val msg = intent.getStringExtra("msg")
        //val texto = findViewById<TextView>(R.id.txtMsg)
        //texto.text = msg

        viewManager = LinearLayoutManager(this)
        viewAdapter = CartoesAdapter(listaCartoes)

        listaCartoes.add(Cartao(1,"América Express",12500.00,15))
        listaCartoes.add(Cartao(2,"NU Bank",9250.00,8))
        listaCartoes.add(Cartao(3,"Credicard ZERO",8850.00,12))
        listaCartoes.add(Cartao(4,"Magazine Luiza",4600.00,5))


        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter

        }


    }
}
