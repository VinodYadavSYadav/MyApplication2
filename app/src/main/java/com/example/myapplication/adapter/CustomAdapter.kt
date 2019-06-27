package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.BindingAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.Categoriesbinding1
import com.example.myapplication.viewmodel.CategoryViewmodel


class CustomAdapter(private  val context: Context,val arrayList: ArrayList<CategoryViewmodel>):RecyclerView.Adapter<CustomAdapter.CustomView>()
{






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {

        val layoutInflater=LayoutInflater.from(parent.context)
        val  catategoriesbinding:Categoriesbinding1=DataBindingUtil.inflate(layoutInflater,
            R.layout.custom_item,parent,false)

        return CustomView(catategoriesbinding)
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {

        val calegoryViewModel=arrayList[position]

        holder.bind(calegoryViewModel)
    }

    override fun getItemCount(): Int {

       return arrayList.size
    }

    class CustomView(val catategoriesbinding: Categoriesbinding1):RecyclerView.ViewHolder(catategoriesbinding.root){


        fun bind(calegoryViewModel: CategoryViewmodel){

            this.catategoriesbinding.categogrymodel=calegoryViewModel
            catategoriesbinding.executePendingBindings()

        }
    }




}