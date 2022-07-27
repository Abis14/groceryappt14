package com.example.groceryapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class spinneradapter(var fruits:ArrayList<String>,var imgs:ArrayList<Any>):
    BaseAdapter() {
    override fun getCount(): Int {
       return fruits.size
    }

    override fun getItem(p0: Int): Any {
        return fruits[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
      var view:View=LayoutInflater.from(p2?.context).inflate(R.layout.spinneritem,p2,false)

        var text:TextView=view.findViewById(R.id.textView3)

        text.setText(fruits[p0])
        return view
    }
}