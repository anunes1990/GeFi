package com.senacrs.gefi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.senacrs.gefi.R
import com.senacrs.gefi.model.Cartao
import com.senacrs.gefi.model.Compra
import com.senacrs.gefi.model.Conta

class ComprasAdapter (private var listaCompras:ArrayList<Compra>) : RecyclerView.Adapter<ComprasAdapter.ComprasHolder>(){

    class ComprasHolder : RecyclerView.ViewHolder {
        var txtDescricao: TextView
        var txtValor: TextView
        var txtVezes: TextView
        var txtParcelaAtual: TextView
        constructor(view: View) :  super(view){
            txtDescricao = view.findViewById(R.id.txtDescricao)
            txtValor = view.findViewById(R.id.txtValor)
            txtVezes = view.findViewById(R.id.txtVezes)
            txtParcelaAtual = view.findViewById(R.id.txtParcelaAtual)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ComprasAdapter.ComprasHolder {
        val view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.compra, parent, false) as View
        return ComprasHolder(view)
    }

    override fun onBindViewHolder(holder: ComprasHolder, position: Int) {
        holder.txtDescricao.text = listaCompras.get(position).descricao
        holder.txtValor.text = listaCompras.get(position).valor.toString()
        holder.txtVezes.text = listaCompras.get(position).vezes.toString()
        holder.txtParcelaAtual.text = listaCompras.get(position).parcelaAtual.toString()
    }

    override fun getItemCount() = listaCompras.size


}