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
        val nasafact=findViewById<ImageView>(R.id.nasafact)
        val button=findViewById<ImageView>(R.id.stars_notes)
        val signup=findViewById<Button>(R.id.signup)
        button.setOnClickListener {
            val intent1=Intent(this,Sqlite::class.java)
            startActivity(intent1)

        }
        signup.setOnClickListener {
            val intent2=Intent(this, Signup::class.java)
            startActivity(intent2)
            Log.i(TAG,"Selected signup")

        }
        mercury.setOnClickListener {
            Log.i(TAG,"Selected Mercury")
        }

        nasafact.setOnClickListener {

        val intent= Intent(this,Imageoftheday::class.java)
            startActivity(intent)
        }
    }



}