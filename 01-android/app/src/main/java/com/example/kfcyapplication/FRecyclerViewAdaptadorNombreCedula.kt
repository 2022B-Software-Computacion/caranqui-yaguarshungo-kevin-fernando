package com.example.kfcyapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorNombreCedula (
    private val contexto: GRecyclerView,
    private val lista: ArrayList<BEntrenador>,
    private val recyclerView: RecyclerView
    ): RecyclerView.Adapter<FRecyclerViewAdaptadorNombreCedula.MyviewHolder>(){
        inner class MyviewHolder(view:View): RecyclerView.ViewHolder(view){
            //ponemos las variables que tenemos en la irtefaz Recycler
            val nombreTextView: TextView
            val cedulaTextView: TextView
            val likesTextView: TextView
            val accionBUtton: Button
            var numeroLikes = 0

            init {
                nombreTextView = view.findViewById(R.id.tv_nombre)
                cedulaTextView = view.findViewById(R.id.tv_cedula)
                likesTextView = view.findViewById(R.id.tv_likes)
                accionBUtton = view.findViewById(R.id.btn_dar_like)
                accionBUtton.setOnClickListener {anadirLike()}
            }
            fun anadirLike(){
                numeroLikes = numeroLikes +1
                likesTextView.text = numeroLikes.toString()
        }
    }

    //setear el layout que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): MyviewHolder{
        val itemView = LayoutInflater //
            .from(parent.context) //obtiene el layoutInflater de un contexto dado
            .inflate(
                R.layout.recycler_view_vista,
                parent,
                false
            )
        return MyviewHolder(itemView)
    }

    //debemos saber extactmente como llenar los datos
    //seteamos los datos para la iteracion
    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val entrenadorActual = this.lista[position]
        holder.nombreTextView.text = entrenadorActual.nombre
        holder.cedulaTextView.text = entrenadorActual.descripcion
        holder.accionBUtton.text = "Like ${entrenadorActual.nombre}"
        holder.likesTextView.text = "0"
    }

    //tamaño del arreglo
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}
