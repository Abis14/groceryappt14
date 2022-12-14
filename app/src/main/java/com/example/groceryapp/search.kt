package com.example.groceryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class search : AppCompatActivity() {
    lateinit var rec: RecyclerView
    lateinit var text: EditText
    lateinit var searc: Button
    lateinit var mylist: ArrayList<listbasicinfo>
    lateinit var listde: ArrayList<listdetails>
    lateinit var adapt: searchpadapter
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.searchmenu)
        rec = findViewById(R.id.recylerviewse)
        text = findViewById(R.id.searchText)
        listde = ArrayList()
        mylist = ArrayList()
        searc = findViewById(R.id.button5)
        searc.setOnClickListener {
            val word = text.text


            database = FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().uid.toString()).child("listbasicinfo")
            database.orderByChild("title").equalTo(word.toString()).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {

                        for (item in snapshot.children) {
                            val data = item.getValue(listbasicinfo::class.java)
                            mylist.add(data!!)
                            Log.d("found", "data found")
                            adapt = searchpadapter(this@search, mylist)

                            rec.adapter = adapt
                            rec.layoutManager = GridLayoutManager(applicationContext, 2);
                            rec.setHasFixedSize(true)


                        }

                    } else {
                        Log.d("fails", "failesssss")
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }





    }
    }






