package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.amplifyframework.core.Amplify

class confirmsignup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmsignup)
        val code=findViewById<Button>(R.id.confirmcode)
        code.setOnClickListener {

        }

    }
}