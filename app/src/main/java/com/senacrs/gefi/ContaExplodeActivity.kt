package com.senacrs.gefi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.senacrs.gefi.adapter.ComprasAdapter
import com.senacrs.gefi.model.Compra

class ContaExplodeActivity : AppCompatActivity() {
    private var listaCompras: ArrayList<Compra> = ArrayList()
    private var idCartao : String? = ""

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conta_explode)

        viewManager = LinearLayoutManager(this)
        viewAdapter = ComprasAdapter(listaCompras)
        idCartao = intent.getStringExtra("idCartao")

        when(idCartao){
            "1" -> {
                listaCompras.add(Compra(1,"Compra 1",150.00, 3,1))
                listaCompras.add(Compra(1,"Compra 5",550.00,5,1))
            }
            "2" -> listaCompras.add(Compra(2,"Compra 2",250.00, 3,1))
            "3" -> listaCompras.add(Compra(3,"Compra 3",350.00, 3,1))
            "4" -> listaCompras.add(Compra(4,"Compra 4",450.00, 3,1))
        }

        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewContaExplode).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    fun lancaConta(view: View?){
        val it = Intent(this, AddContaActivity::class.java).apply {
            putExtra("idCartao", idCartao)
    }
        startActivityForResult(it, 1)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                val compra = data?.getSerializableExtra("compra") as Compra
                listaCompras.add(compra)
                viewAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Compra registrada com sucesso!", Toast.LENGTH_SHORT).show()
            }
        }

    }



}
