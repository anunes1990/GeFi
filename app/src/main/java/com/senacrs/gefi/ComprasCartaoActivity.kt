package com.senacrs.gefi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.senacrs.gefi.adapter.CompraCartaoAdapter
import com.senacrs.gefi.daos.CartaoDao
import com.senacrs.gefi.database.AppDatabase
import com.senacrs.gefi.model.Cartao

class ComprasCartaoActivity : AppCompatActivity() {
    private var listaCartoes: ArrayList<Cartao> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    var db: AppDatabase? = null
    var dao: CartaoDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compra_cartao)
        supportActionBar!!.title = "Cartões"

        db = Room.databaseBuilder(this, AppDatabase::class.java, "myDB")
            .allowMainThreadQueries().build()
        dao = db?.cartaoDao()

        viewManager = LinearLayoutManager(this)
        viewAdapter = CompraCartaoAdapter(listaCartoes)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewContas).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        val retorno: List<Cartao>? = dao?.cartoesFindAll()
        retorno?.map { r -> listaCartoes.add(r) }
        viewAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)
        listaCartoes.removeAll(listaCartoes)

        val retorno: List<Cartao>? = dao?.cartoesFindAll()
        retorno?.map { r -> listaCartoes.add(r) }
        viewAdapter.notifyDataSetChanged()
    }

    fun goExplode(view:View){
        var intent = Intent(this, ListaComprasActivity::class.java).apply {
            putExtra("idCartao", view.findViewById<TextView>(R.id.txtIdCartao).getText().toString())
        }
        startActivityForResult(intent, 1)
    }

}
