package com.example.finalproject

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult
import com.amplifyframework.core.Amplify

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel:MainActivityViewModel
private lateinit var name:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mercury=findViewById<ImageView>(R.id.imageViewmercury)
        viewModel= ViewModelProvider(this)[MainActivityViewModel::class.java]
        name=findViewById(R.id.name)

        this.loadthetext()
        name.text=viewModel.name
        val nasafact=findViewById<ImageView>(R.id.nasafact)
        val button=findViewById<ImageView>(R.id.stars_notes)
        val signout=findViewById<Button>(R.id.Signout)
        val earth=findViewById<ImageView>(R.id.earth)
        val clickme=findViewById<Button>(R.id.clickme)


        clickme.setOnClickListener {
            val intent5=Intent(this,Clickme::class.java)
            startActivity(intent5)
        }
        earth.setOnClickListener{
        val intent4=Intent(this,Earth::class.java)
            startActivity(intent4)
        }
        button.setOnClickListener {
            val intent1=Intent(this,Sqlite::class.java)
            startActivity(intent1)

        }
        signout.setOnClickListener {
            Amplify.Auth.signOut { signOutResult ->
                when(signOutResult) {
                    is AWSCognitoAuthSignOutResult.CompleteSignOut -> {
                        // Sign Out completed fully and without errors.
                        Log.i("AuthQuickStart", "Signed out successfully");
                        val intent2=Intent(this, Signup::class.java)
                        startActivity(intent2)
                    }
                    is AWSCognitoAuthSignOutResult.PartialSignOut -> {
                        // Sign Out completed with some errors. User is signed out of the device.
                        signOutResult.hostedUIError?.let {
                            Log.e("AuthQuickStart", "HostedUI Error", it.exception)
                            // Optional: Re-launch it.url in a Custom tab to clear Cognito web session.

                        }
                        signOutResult.globalSignOutError?.let {
                            Log.e("AuthQuickStart", "GlobalSignOut Error", it.exception)
                            // Optional: Use escape hatch to retry revocation of it.accessToken.
                        }
                        signOutResult.revokeTokenError?.let {
                            Log.e("AuthQuickStart", "RevokeToken Error", it.exception)
                            // Optional: Use escape hatch to retry revocation of it.refreshToken.
                        }
                    }
                    is AWSCognitoAuthSignOutResult.FailedSignOut -> {
                        // Sign Out failed with an exception, leaving the user signed in.
                        Log.e("AuthQuickStart", "Sign out Failed", signOutResult.exception)
                    }
                }
            }


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