package com.senacrs.gefi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.senacrs.gefi.adapter.ComprasAdapter
import com.senacrs.gefi.daos.CompraDao
import com.senacrs.gefi.database.AppDatabase
import com.senacrs.gefi.model.Cartao
import com.senacrs.gefi.model.Compra

class ListaComprasActivity : AppCompatActivity() {
    private var listaCompras: ArrayList<Compra> = ArrayList()
    private var idCartao : String? = ""

    var db: AppDatabase? = null
    var dao: CompraDao? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_compras)
        supportActionBar!!.title = "Compras"


        db = Room.databaseBuilder(this, AppDatabase::class.java, "myDB")
            .allowMainThreadQueries().build()
        dao = db?.compraDao()

        viewManager = LinearLayoutManager(this)
        viewAdapter = ComprasAdapter(listaCompras)

        idCartao = intent.getStringExtra("idCartao")

        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewContaExplode).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        val retorno: List<Compra>? = dao?.comprasCartaoFindAll(idCartao.toString().toLong())
        retorno?.map { r -> listaCompras.add(r) }
        viewAdapter.notifyDataSetChanged()

    }

    fun lancaConta(view: View?){
        val it = Intent(this, AddCompraActivity::class.java).apply {
            putExtra("idCartao", idCartao)
    }
        startActivityForResult(it, 1)
    }

    fun config(view: View){
        val it = Intent(this, ConfigCompraActivity::class.java).apply {
            val descricao = view.findViewById<TextView>(R.id.txtDescricao).text.toString()
            val compra = listaCompras.find { c -> c.descricao == descricao } as Compra
            putExtra("idCartao", idCartao)
            putExtra("idCompra", compra.id.toString())
        }
        startActivityForResult(it, 2)
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
        } else if (requestCode == 2){
            if(resultCode == Activity.RESULT_OK){
                val compra = data?.getSerializableExtra("compra") as Compra
                val compraOld = listaCompras.find { c -> c.id == compra.id }
                listaCompras.remove(compraOld)
                listaCompras.add(compra)
                viewAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Compra editado com sucesso!", Toast.LENGTH_SHORT).show()
            } else if(resultCode == 666){
                val compra = data?.getSerializableExtra("compra") as Compra
                val compraOld = listaCompras.find { c -> c.id == compra.id }
                listaCompras.remove(compraOld)
                viewAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Compra removido com sucesso!", Toast.LENGTH_SHORT).show()
            }
        }

    }



}
