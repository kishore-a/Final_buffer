package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.amplifyframework.core.Amplify
import org.w3c.dom.Text

class Signin : AppCompatActivity() {
    private lateinit var viewModel:MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        viewModel=ViewModelProvider(this).get(MainActivityViewModel::class.java)
        val signin=findViewById<Button>(R.id.signin)
        val username=findViewById<EditText>(R.id.usernamesignin)
        val password=findViewById<EditText>(R.id.passwordsignin)
        val signinp=findViewById<TextView>(R.id.Su)

        signinp.setOnClickListener {
            val intent2=Intent(this,Signup::class.java)
            startActivity(intent2)
        }
        signin.setOnClickListener{
            Amplify.Auth.signIn(username.text.toString(), password.text.toString(),
                { result ->
                    if (result.isSignedIn) {
                        Log.i("AuthQuickstart", "Sign in succeeded");
                        viewModel.updateName(username.text.toString(),);
                        val intent= Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Log.i("AuthQuickstart", "Sign in not complete")
                        Toast.makeText(this,"Cannot Signin",Toast.LENGTH_SHORT).show()
                    }
                },
                { Log.e("AuthQuickstart", "Failed to sign in", it) }
            )
        }
    }
}