package com.example.canva

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //boton Buscar
        val sVBuscar = findViewById<SearchView>(R.id.sv_buscar)
        sVBuscar
            .setOnClickListener{
                //llamamos a la función Buscar
                //TERMINAR
                onQueryTextSubmit("")
            }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView1)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false
        )
        val slides = listOf(R.drawable.slide1, R.drawable.slide2, R.drawable.slide3) // lista de recursos de imágenes
        val adapter = SlidesAdapter(slides)
        recyclerView.adapter = adapter

    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

}
