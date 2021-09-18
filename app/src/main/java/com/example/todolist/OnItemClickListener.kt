package com.example.todolist

import android.view.View

//SAM interface (click listener) for itemlist click event
fun interface OnItemClickListener{
    fun onClick(view: View, position: Int)
}



