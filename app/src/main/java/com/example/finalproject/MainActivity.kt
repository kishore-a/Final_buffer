package com.example.finalproject

import android.content.ContentValues.TAG
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mercury=findViewById<ImageView>(R.id.imageViewmercury)
        val earthbutton=findViewById<ImageView>(R.id.earth)
        val button=findViewById<Button>(R.id.button2)
        button.setOnClickListener {
            val intent1=Intent(this,Sqlite::class.java)
            startActivity(intent1)

        }
        mercury.setOnClickListener {
            Log.i(TAG,"Selected Mercury")
        }

        earthbutton.setOnClickListener {
        val intent= Intent(this,Imageoftheday::class.java)
            startActivity(intent)
        }
    }


//    private fun getmydata(){
//
//        val retrofitBuilder= Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build().create(Apinterface::class.java)
////        val retrofit = retrofitBuilder.getData()
//
//        retrofit.enqueue(object : Callback<Pic> {
//            override fun onResponse(call: Call<Pic>, response: Response<Pic>) {
//                var imageuri=""
//                var image=findViewById<ImageView>(R.id.ImageViewfromapi)
//                val responsebody=response.body()
//                val textapi=findViewById<TextView>(R.id.textapi)
//
//
//                val myStringBuilder =StringBuilder()
//                if (responsebody != null) {
//                    imageuri=responsebody.hdurl
//                    Picasso.get().load(imageuri).into(image)
//                    Log.i(TAG,"Image load")
//                }
//
//
//             }
//
//            override fun onFailure(call: Call<Pic>, t: Throwable) {
//                Log.i(TAG,"On Failure "+t.message)
//            }
//        })
//    }
}