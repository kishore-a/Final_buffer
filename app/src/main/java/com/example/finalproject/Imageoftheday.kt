package com.example.finalproject

import android.content.ContentValues
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.ImageView
import com.example.practiceproject.UserTextInputModel

const val BASE_URL="https://api.nasa.gov/planetary/"
class Imageoftheday : AppCompatActivity() {
    private lateinit var comment: TextView
    private lateinit var button: Button
    private var textInputModel:UserTextInputModel= UserTextInputModel(this)

    private lateinit var edittext: EditText
    private fun savethetext(s:String){
        this.textInputModel.saveText(s)
    }
private fun loadthetext()
{
    var date=this.textInputModel.loadText()
    var final_url= BASE_URL+"apod?date="+date+"&api_key=DEMO_KEY"
    val retrofitBuilder =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(Apinterface::class.java)
    val retrofit = retrofitBuilder.getData(final_url)
    retrofit.enqueue(object : Callback<Pic> {
        override fun onResponse(call: Call<Pic>, response: Response<Pic>) {


            val responsebody = response.body()
            Log.i(TAG,responsebody.toString())
            2

            if (responsebody != null) {
                comment.text = responsebody.explanation
                Log.i(TAG,"Comment updated")
            }
            else{
                Log.i(TAG,"Null")
            }

        }

        override fun onFailure(call: Call<Pic>, t: Throwable) {
            Log.i(ContentValues.TAG, "On Failure " + t.message)
        }
    })
}    private fun setCallbacks() {

        this.button.setOnClickListener {

            var date = edittext.text.toString()
            val t=date
            if(t!=null){
                this.savethetext(t)
            }
            var final_url= BASE_URL+"apod?date="+date+"&api_key=DEMO_KEY"
            val retrofitBuilder =
                Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL).build().create(Apinterface::class.java)
            val retrofit = retrofitBuilder.getData(final_url)
            retrofit.enqueue(object : Callback<Pic> {
                override fun onResponse(call: Call<Pic>, response: Response<Pic>) {


                    val responsebody = response.body()
                    Log.i(TAG,responsebody.toString())
2

                    if (responsebody != null) {
                        comment.text = responsebody.explanation
                        Log.i(TAG,"Comment updated")
                    }
                    else{
                        Log.i(TAG,"Null")
                    }

                }

                override fun onFailure(call: Call<Pic>, t: Throwable) {
                    Log.i(ContentValues.TAG, "On Failure " + t.message)
                }
            })


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imageoftheday)
        button = findViewById(R.id.button)
        comment = findViewById(R.id.comment)
        edittext = findViewById(R.id.edittext)
        loadthetext()
        this.setCallbacks()


//        button.setOnClickListener {
//    val retrofitBuilder= Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build().create(Apinterface::class.java)
//    val retrofit = retrofitBuilder.getData()
//
//    retrofit.enqueue(object : Callback<Pic> {
//        override fun onResponse(call: Call<Pic>, response: Response<Pic>) {
//
//
//
//            var imageuri=""
//            var image=findViewById<ImageView>(R.id.ImageViewfromapi)
//            val responsebody=response.body()
//            val textapi=findViewById<TextView>(R.id.textapi)
//
//
//            val myStringBuilder =StringBuilder()
//            if (responsebody != null) {
//                imageuri=responsebody.hdurl
//                Log.i(ContentValues.TAG,"Image Loaded")
//            }
//            Picasso.get().load(imageuri).into(imageView)
//
//        }
//
//        override fun onFailure(call: Call<Pic>, t: Throwable) {
//            Log.i(ContentValues.TAG,"On Failure "+t.message)
//        }
//    })


    }

}
