package com.senacrs.gefi

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
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

    fun addCartao(view: View){
        val it = Intent(this, AddCartaoActivity::class.java)
        startActivityForResult(it, 1)
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
