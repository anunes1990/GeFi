package com.senacrs.gefi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.senacrs.gefi.adapter.ComprasAdapter
import com.senacrs.gefi.adapter.ContasAdapter
import com.senacrs.gefi.model.Compra
import com.senacrs.gefi.model.Conta

class ContaExplodeActivity : AppCompatActivity() {
    private var listaCompras: ArrayList<Compra> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conta_explode)

        viewManager = LinearLayoutManager(this)
        viewAdapter = ComprasAdapter(listaCompras)

        val idCartao = intent.getStringExtra("idCartao")

        when(idCartao){
            "1" -> {
                listaCompras.add(Compra(1,"Compra 1",150.00, 3,1))
                listaCompras.add(Compra(1,"Compra 5",550.00, 5,1))
            }
            "2" -> listaCompras.add(Compra(2,"Compra 2",250.00, 3,1))
            "3" -> listaCompras.add(Compra(3,"Compra 3",350.00, 3,1))
            "4" -> listaCompras.add(Compra(4,"Compra 4",450.00, 3,1))
        }

//        listaCompras.add(Compra(1,"Compra 1",150.00, 3,1))
//        listaCompras.add(Compra(2,"Compra 2",250.00, 3,1))
//        listaCompras.add(Compra(3,"Compra 3",350.00, 3,1))
//        listaCompras.add(Compra(4,"Compra 4",450.00, 3,1))

        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewContaExplode).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }


    }
}
