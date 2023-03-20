package com.kfcy.cantante

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    val db = DataBase()
    var idItemSeleccionado = 0
    lateinit var singers: List<Singer>
    lateinit var singersName: ArrayList<String>
    lateinit var sListView: ListView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.sListView = findViewById<ListView>(R.id.id_listview_singers)
        getSingers()
        // add context menu
        registerForContextMenu(sListView)
        val btnAddSinger: Button = findViewById<Button>(R.id.id_add_singer)
        btnAddSinger.setOnClickListener {
            editAddSinger(null)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.context_menu_1, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onResume() {
        super.onResume()
        getSingers()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.edit -> {
                editAddSinger(singers!![idItemSeleccionado])
                return true
            }
            R.id.delete -> {
                deleteSinger()
                return true
            }

            R.id.show_songs -> {
                showSongs()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun editAddSinger(
        singer: Singer?
    ){
        val intent = Intent(this, EditAddSingerActivity::class.java)
        intent.putExtra("singer", singer)
        startActivity(intent)
    }

    fun deleteSinger (){
        val singer = idItemSeleccionado
        val idSinger = singers[singer].id
        db.deleteSinger(idSinger!!,
            onSuccess = {
                singers = singers.filterIndexed { index, _ -> index != singer }
                singersName.removeAt(singer)
                (sListView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
            },
            onFailure = {})

    }

    fun showSongs(){
        val singer = singers[idItemSeleccionado]
        val intent = Intent(this, ShowSongsActivity::class.java)
        intent.putExtra("idSinger", singer.id)
        intent.putExtra("nameSinger", singer.name)
        startActivity(intent)
    }

    fun getSingers(){
        db.getSingers(
            onSuccess = { singers ->
                this.singers = singers
                singersName = singers.map { it.name } as ArrayList<String>
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, singersName)

                // Asignar el adapter al ListView
                sListView.adapter = adapter
                adapter.notifyDataSetChanged()
            },
            onFailure = { }
        )
    }
}