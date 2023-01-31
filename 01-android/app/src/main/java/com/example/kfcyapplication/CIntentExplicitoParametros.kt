package com.example.kfcyapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import kotlin.math.log

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("edad", 0)
        val entrenador = intent.getParcelableExtra<BEntrenador>(
            "entrenadorPrincipal"
        )
        val boton = findViewById<Button>(R.id.btn_devolver_respuesta)
        boton
            .setOnClickListener { devolverRespuesta() }
    }
    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("nombreModificado", "Vicente")
        intentDevolverParametros.putExtra("edadModificado", 33)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }
}