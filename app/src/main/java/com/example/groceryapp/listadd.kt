package com.example.groceryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toolbar

class listadd : AppCompatActivity() {
   lateinit var addbtn:Button
    lateinit var framelayouts: FrameLayout
    lateinit var titles:String
    lateinit var mfragment:addtolist
    private val fragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
//        var customtitle=requestWindowFeature(
//            Window.FEATURE_CUSTOM_TITLE)
        setContentView(R.layout.activity_listadd)
        super.onCreate(savedInstanceState)



        addbtn=findViewById(R.id.plus)
        framelayouts=findViewById(R.id.details)
        loademptyfragment()

//       if(customtitle)
//       {
//           window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.listactionbar)
//       }
//        var title:TextView=findViewById(R.id.textView5)
        titles= intent.getStringExtra("data").toString()


        addbtn.setOnClickListener{
            loadfragment()
        }
        titles= intent.getStringExtra("data").toString()

mfragment=addtolist()
    }

    private fun loademptyfragment() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.details, frame())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun loadfragment() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        val bundle=Bundle()
        bundle.putString("title",titles)
         mfragment.arguments=bundle

        fragmentTransaction.replace(R.id.details, mfragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}