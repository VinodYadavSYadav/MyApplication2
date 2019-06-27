package com.example.myapplication.viewmodel

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.myapplication.Api
import com.example.myapplication.model.Category
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class CategoryViewmodel : ViewModel {

    var title=""
    var description=""
    var imageHref=""


    constructor() : super()
    constructor(categories: Category) : super() {
        this.title = categories.title
        this.description = categories.description
        this.imageHref = categories.imageHref
    }

    var arrayListMuttabe= MutableLiveData<ArrayList<CategoryViewmodel>>()
    var arrayList=ArrayList<CategoryViewmodel>()

    /*   fun getImageUrl():String{
           return  imagepath

       }*/

    fun getImageUrl(): String {
        // The URL will usually come from a model (i.e Profile)
        return "http://cdn.meme.am/instances/60677654.jpg"
    }



    fun getArrayList(): MutableLiveData<ArrayList<CategoryViewmodel>> {




        gettid()






        return gettid()
    }



    fun gettid(): MutableLiveData<ArrayList<CategoryViewmodel>> {
        println("ffffffffffffffffffhhhhhhhhhh")

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val api = retrofit.create(Api::class.java!!)

        val call = api.getString()

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("Responsestring", response.body().toString())
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString())

                        val jsonresponse = response.body().toString()
                        // writeTv(jsonresponse)

                        val obj = JSONObject(jsonresponse)

                        val dataArray = obj.getJSONArray("rows")
                        arrayList.clear()

                        for (i in 0 until dataArray.length()) {

                            //   val retroModel = RetroModel()
                            val dataobj = dataArray.getJSONObject(i)

                            val categories1=Category(dataobj.getString("title"),dataobj.getString("description"),dataobj.getString("imageHref"))

                            val calegoryViewModel:CategoryViewmodel= CategoryViewmodel(categories1)
                            arrayList.add(calegoryViewModel)


                        }
                        arrayListMuttabe.value=arrayList


                    } else {
                        Log.i(
                            "onEmptyResponse",
                            "Returned empty response"
                        )//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i(
                    "onEmptyResponse",
                    "Returned empty response"
                )
            }
        })

        return arrayListMuttabe
    }



}

object ImageBindingAdapter{
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(view: ImageView, url:String){

        Glide.with(view.context).load(url)

            .thumbnail(0.5f)
            .into(view)

    }
}