package com.kfcy.cantante

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*

class ShowSongsActivity : AppCompatActivity() {

    lateinit var songsListView: ListView
    lateinit var singerNameTextView: TextView

    val db = DataBase()
    var idItemSeleccionado = 0
    var songs: List<Song>? = emptyList()
    lateinit var songsName: ArrayList<String>
    lateinit var singerName: String
    lateinit var idSinger: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_songs)

        singerNameTextView = findViewById(R.id.id_textview_singer_name)
        songsListView = findViewById(R.id.id_listview_songs)

        idSinger = intent.getStringExtra("idSinger")!!
        singerName = intent.getStringExtra("nameSinger")!!
        singerNameTextView.text = singerName
        db.getSongsOfSinger(idSinger,
            onSuccess = {
            this.songs = it
                songsName = songs?.map { it.name } as ArrayList<String>
                val adapter =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, songsName as List<String>)

                songsListView.adapter = adapter

                singerNameTextView.text = singerName
                adapter.notifyDataSetChanged()

                registerForContextMenu(songsListView)
            }, onFailure = {})



        val btnAddSong = findViewById<Button>(R.id.id_add_song)
        btnAddSong.setOnClickListener {
            editAddSong(null)
        }
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.context_menu_2, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.edit -> {
                editAddSong(songs!![idItemSeleccionado])
                return true
            }
            R.id.delete -> {
                deleteSong()
                return true
            }


            else -> super.onContextItemSelected(item)
        }
    }

    fun editAddSong(
        song: Song?
    ) {
        val intent = Intent(this, EditAddSongActivity::class.java)
        intent.putExtra("idSinger", idSinger)
        intent.putExtra("nameSinger", singerName)
        intent.putExtra("song", song)
        startActivity(intent)
    }

    fun deleteSong() {
        val songIndex = idItemSeleccionado
        val idSong = songs!![idItemSeleccionado].id!!
        db.deleteSong(
            idSinger, idSong,
            onSuccess = {
                songs = songs?.filterIndexed { index, _ -> index != songIndex }
                songsName.removeAt(songIndex)
                (songsListView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
            },
            onFailure = { }
        )
    }
}