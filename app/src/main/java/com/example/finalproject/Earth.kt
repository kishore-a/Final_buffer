package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Earth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_earth)

        val video=findViewById<Button>(R.id.video1)
        video.setOnClickListener{
            val intent1= Intent(this,VideoDescription::class.java)
            startActivity(intent1)
        }
    }
}