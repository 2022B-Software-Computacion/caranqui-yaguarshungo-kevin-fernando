package com.example.a02_deber_iib.HelperClasses


import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable

class phonehelper(val color: GradientDrawable, val image: Int, val title: String) {

    fun getImage(): Int {
        return image
    }

    fun getTitle(): String {
        return title
    }

    fun getGradient(): Drawable {
        return color
    }
}
