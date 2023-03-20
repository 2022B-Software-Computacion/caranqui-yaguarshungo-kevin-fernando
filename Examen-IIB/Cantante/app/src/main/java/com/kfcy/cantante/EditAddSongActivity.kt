package com.kfcy.cantante


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


class EditAddSongActivity : AppCompatActivity() {

    val db = DataBase()
    lateinit var idSinger: String
    lateinit var nameSinger: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_add_song)

        val nameInput = findViewById<EditText>(R.id.id_name_input)
        val albumInput = findViewById<EditText>(R.id.id_album_input)
        val dateInput = findViewById<EditText>(R.id.id_date_input)
        val addSong = findViewById<Button>(R.id.id_add_edit_song)

        idSinger = intent.getStringExtra("idSinger")!!
        nameSinger = intent.getStringExtra("nameSinger")!!
        val song = intent.getParcelableExtra<Song>("song")

        if (song != null){
            nameInput.setText(song.name)
            albumInput.setText(song.album)
            dateInput.setText(song.releaseDate)
            addSong.setOnClickListener {
                val name = nameInput.text.toString()
                val album = albumInput.text.toString()
                val date = dateInput.text.toString()
                val newSong = Song(song.id ,name, album, date)
                db.updateSong(idSinger,newSong,{
                    val intent = Intent(this,ShowSongsActivity::class.java)
                    intent.putExtra("idSinger",idSinger)
                    intent.putExtra("nameSinger",nameSinger)
                    startActivity(intent)
                }, { })

            }
        }else{
            addSong.setOnClickListener {
                val name = nameInput.text.toString()
                val album = albumInput.text.toString()
                val date = dateInput.text.toString()
                val newSong = Song(name, album, date)
                db.addSong(idSinger ,newSong,
                    {
                        val intent = Intent(this,ShowSongsActivity::class.java)
                        intent.putExtra("idSinger",idSinger)
                        intent.putExtra("nameSinger",nameSinger)
                        startActivity(intent)
                    }
                    , {})
            }
        }
    }
}