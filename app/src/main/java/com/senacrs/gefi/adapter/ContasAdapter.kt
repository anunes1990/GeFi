package com.senacrs.gefi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.senacrs.gefi.R
import com.senacrs.gefi.model.Cartao
import com.senacrs.gefi.model.Conta

class ContasAdapter (private var listaContas:ArrayList<Conta>) : RecyclerView.Adapter<ContasAdapter.ContasHolder>(){

    class ContasHolder : RecyclerView.ViewHolder {
        var txtIdCartao: TextView
        var txtNomeCartao: TextView
        var txtVencimento: TextView
        var txtValor: TextView
        constructor(view: View) :  super(view){
            txtIdCartao = view.findViewById(R.id.txtIdCartao)
            txtNomeCartao = view.findViewById(R.id.txtNomeCartao)
            txtVencimento = view.findViewById(R.id.txtVencimento)
            txtValor = view.findViewById(R.id.txtValor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ContasAdapter.ContasHolder {
        val view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.conta, parent, false) as View
        return ContasHolder(view)
    }

    override fun onBindViewHolder(holder: ContasHolder, position: Int) {
        holder.txtIdCartao.text = listaContas.get(position).idCartao.toString()
        holder.txtNomeCartao.text = listaContas.get(position).nome
        holder.txtVencimento.text = listaContas.get(position).diaVencimento.toString()
        holder.txtValor.text = listaContas.get(position).valor.toString()
    }

    override fun getItemCount() = listaContas.size


}