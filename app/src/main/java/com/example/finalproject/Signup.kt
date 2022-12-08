package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.core.Amplify

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val signUp=findViewById<Button>(R.id.signUp)
        val email=findViewById<EditText>(R.id.email)
        val username=findViewById<EditText>(R.id.username)
        val password=findViewById<EditText>(R.id.password)
        val confirm_code=findViewById<EditText>(R.id.code)
        val code =findViewById<Button>(R.id.codeconfirm)
        val signin=findViewById<TextView>(R.id.signinsignup)
        signin.setOnClickListener{
            val intent1= Intent(this,Signin::class.java)
            startActivity(intent1)
        }
        code.setOnClickListener {
            Amplify.Auth.confirmSignUp(
                username.text.toString(), confirm_code.text.toString(),
                { result ->
                    if (result.isSignUpComplete) {
                        Log.i("AuthQuickstart", "Confirm signUp succeeded")
                        val intent2=Intent(this,Signin::class.java)
                        startActivity(intent2)
                    } else {
                        Log.i("AuthQuickstart","Confirm sign up not complete")
                    }
                },
                { Log.e("AuthQuickstart", "Failed to confirm sign up", it) }
            )

        }
        signUp.setOnClickListener {
            val options = AuthSignUpOptions.builder()
                .userAttribute(AuthUserAttributeKey.email(), email.text.toString())
                .build()
            Amplify.Auth.signUp(username.text.toString(), password.text.toString(), options,
                { Log.i("AuthQuickStart", "Sign up succeeded: $it");


                },
                { Log.e ("AuthQuickStart", "Sign up failed", it) }
            )
        }
    }
}