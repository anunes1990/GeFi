package com.senacrs.gefi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.senacrs.gefi.R
import com.senacrs.gefi.model.Cartao

class CartoesAdapter (private var listaCartoes:ArrayList<Cartao>) : RecyclerView.Adapter<CartoesAdapter.CartoesHolder>(){

    class CartoesHolder : RecyclerView.ViewHolder {
        var txtNomeCartao: TextView
        var txtLimite: TextView
        constructor(view: View) :  super(view){
            txtNomeCartao = view.findViewById(R.id.txtNomeCartao)
            txtLimite = view.findViewById(R.id.txtLimite)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CartoesAdapter.CartoesHolder {
        val view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.cartao, parent, false) as View
        return CartoesHolder(view)
    }

    override fun onBindViewHolder(holder: CartoesHolder, position: Int) {
        holder.txtNomeCartao.text = listaCartoes.get(position).nome
        holder.txtLimite.text = listaCartoes.get(position).limite.toString()
    }

    override fun getItemCount() = listaCartoes.size


}