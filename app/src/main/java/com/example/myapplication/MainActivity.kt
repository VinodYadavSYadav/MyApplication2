package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.CustomAdapter
import com.example.myapplication.viewmodel.CategoryViewmodel

class MainActivity : AppCompatActivity() {

    private var recyclerView:RecyclerView?=null
    private var customAdapter:CustomAdapter?=null
    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.recycl)
        var categoryViewModel:CategoryViewmodel=ViewModelProviders.of(this).get(CategoryViewmodel::class.java)


        categoryViewModel.getArrayList().observe(this, Observer {categoryViewModel->


            customAdapter =CustomAdapter(this,categoryViewModel!!)

            // linearLayoutManager = LinearLayoutManager(this)
            recyclerView!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            // recyclerView!!.layoutManager = LinearLayoutManager(this) // default orientation is vertical
            recyclerView!!.adapter=customAdapter

            println("other message"+categoryViewModel.size)



        })

    }
}
