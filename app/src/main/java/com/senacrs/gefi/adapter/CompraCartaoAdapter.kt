package com.senacrs.gefi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.senacrs.gefi.R
import com.senacrs.gefi.model.Cartao

class CompraCartaoAdapter (private var listaContas:ArrayList<Cartao>) : RecyclerView.Adapter<CompraCartaoAdapter.ContasHolder>(){

    class ContasHolder : RecyclerView.ViewHolder {
        var txtNomeCartao: TextView
        var txtVencimento: TextView
        var txtValor: TextView
        var txtLimiteDisponivel: TextView
        constructor(view: View) :  super(view){
            txtNomeCartao = view.findViewById(R.id.txtNomeCartao)
            txtVencimento = view.findViewById(R.id.txtVencimento)
            txtValor = view.findViewById(R.id.txtValor)
            txtLimiteDisponivel = view.findViewById(R.id.txtLimiteDisponivel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CompraCartaoAdapter.ContasHolder {
        val view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.compra_cartao, parent, false) as View
        return ContasHolder(view)
    }

    override fun onBindViewHolder(holder: ContasHolder, position: Int) {
        holder.txtNomeCartao.text = listaContas.get(position).nome
        holder.txtVencimento.text = listaContas.get(position).diaVencimento.toString()
        holder.txtValor.text = listaContas.get(position).valorGasto.toString()
        holder.txtLimiteDisponivel.text = listaContas.get(position).limiteDisponivel.toString()
    }

    override fun getItemCount() = listaContas.size


}