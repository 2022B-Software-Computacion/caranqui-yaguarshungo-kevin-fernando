package com.jarves.sliderecycler

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a02_deber_iib.HelperClasses.adapterphone
import com.example.a02_deber_iib.HelperClasses.phonehelper
import com.example.a02_deber_iib.R

class MainActivity : AppCompatActivity(), adapterphone.ListItemClickListener {
    private lateinit var phoneRecycler: RecyclerView
    private lateinit var adapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hooks
        phoneRecycler = findViewById(R.id.my_recycler)
        phoneRecycler()

    }

    private fun phoneRecycler() {
        // All Gradients
        val gradient2 = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(0xffd4cbe5.toInt(), 0xffd4cbe5.toInt())
        )
        val gradient1 = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(0xff7adccf.toInt(), 0xff7adccf.toInt())
        )
        val gradient3 = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(0xfff7c59f.toInt(), 0xffF7c59f.toInt())
        )
        val gradient4 = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(0xffb8d7f5.toInt(), 0xffb8d7f5.toInt())
        )

        phoneRecycler.setHasFixedSize(true)
        phoneRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val phonelocations = ArrayList<phonehelper>()
        phonelocations.add(phonehelper(gradient1, R.drawable.samsung, "Samsung"))
        phonelocations.add(phonehelper(gradient4, R.drawable.vivo, "Vivo"))
        phonelocations.add(phonehelper(gradient2, R.drawable.apple, "Apple"))
        phonelocations.add(phonehelper(gradient4, R.drawable.realme, "Realme"))
        phonelocations.add(phonehelper(gradient2, R.drawable.poco, "Poco"))

        adapter = adapterphone(phonelocations, this)
        phoneRecycler.adapter = adapter
    }

    override fun onphoneListClick(clickedItemIndex: Int) {
        val mIntent: Intent
        when (clickedItemIndex) {
            0 -> { // first item in Recycler view
                mIntent = Intent(this@MainActivity, samsung::class.java)
                startActivity(mIntent)
            }
            1 -> { // second item in Recycler view
                mIntent = Intent(this@MainActivity, vivo::class.java)
                startActivity(mIntent)
            }
            2 -> { // third item in Recycler view
                mIntent = Intent(this@MainActivity, apple::class.java)
                startActivity(mIntent)
            }
            3 -> { // fourth item in Recycler view
                mIntent = Intent(this@MainActivity, realme::class.java)
                startActivity(mIntent)
            }
            4 -> { // fifth item in Recycler view
                mIntent = Intent(this@MainActivity, poco::class.java)
                startActivity(mIntent)
            }
        }
    }
    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
