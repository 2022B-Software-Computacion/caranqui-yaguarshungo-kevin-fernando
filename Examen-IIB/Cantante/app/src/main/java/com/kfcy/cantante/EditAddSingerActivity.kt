package com.kfcy.cantante


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


class EditAddSingerActivity : AppCompatActivity() {
    val db = DataBase()
    lateinit var idSinger: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_add_singer)

        val nameInput = findViewById<EditText>(R.id.id_name_input)
        val lNameInput = findViewById<EditText>(R.id.id_lName_input)
        val addBtn = findViewById<Button>(R.id.id_add_edit_singer)

        val singer = intent.getParcelableExtra<Singer>("singer")

        if (singer != null) {
            idSinger = singer.id!!
            nameInput.setText(singer.name)
            lNameInput.setText(singer.lastn)
            addBtn.setOnClickListener {
                val name = nameInput.text.toString()
                val lname = lNameInput.text.toString()
                val newSinger = Singer(singer.id, name, lname)
                db.updateSinger(idSinger, newSinger, {
                    startActivity(Intent(this,MainActivity::class.java))
                }, { })

            }
        }else{
            addBtn.setOnClickListener {
                val name = nameInput.text.toString()
                val lname = lNameInput.text.toString()

                val singer = Singer(name,lname)
                db.createSinger(singer,
                    {
                        singer.id = it
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                    , { })
            }
        }
    }
}