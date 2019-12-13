package com.senacrs.gefi

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.senacrs.gefi.adapter.CartoesAdapter
import com.senacrs.gefi.daos.CartaoDao
import com.senacrs.gefi.database.AppDatabase
import com.senacrs.gefi.model.Cartao

class CartoesActivity : AppCompatActivity() {
    private var listaCartoes: ArrayList<Cartao> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    var db: AppDatabase? = null
    var dao: CartaoDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartoes)

        db = Room.databaseBuilder(this, AppDatabase::class.java, "myDB")
            .allowMainThreadQueries().build()
        dao = db?.cartaoDao()

        viewManager = LinearLayoutManager(this)
        viewAdapter = CartoesAdapter(listaCartoes)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        val retorno: List<Cartao>? = dao?.cartoesFindAll()
        retorno?.map { r -> listaCartoes.add(r) }
        viewAdapter.notifyDataSetChanged()

    }

    fun addCartao(view: View?){
        val it = Intent(this, AddCartaoActivity::class.java)
        startActivityForResult(it, 1)
    }

    fun excluirCartao(view: View?){
        val nome = findViewById<TextView>(R.id.txtNomeCartao).text.toString()
        Log.d("aqui", nome)

        val elemento = listaCartoes.find{c -> c.nome ==  nome} as Cartao
        Log.d("aqui", elemento.id.toString())

        dao?.deleteCartao(elemento)
        listaCartoes.remove(elemento)
        viewAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                val cartao = data?.getSerializableExtra("cartao") as Cartao
                listaCartoes.add(cartao)
                viewAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Cartão registrado com sucesso!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
