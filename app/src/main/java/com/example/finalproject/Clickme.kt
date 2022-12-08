package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.practiceproject.UserTextInputModel

class Clickme : AppCompatActivity() {
    private lateinit var comment: TextView
    private lateinit var buttonsaved:Button
    private lateinit var comments: EditText

    private var textInputModel: UserTextInputModel1 = UserTextInputModel1(this)
    private fun savethetext(s:String){
        this.textInputModel.saveText(s)
    }
    private fun loadthetext(){
        this.comment.text=this.textInputModel.loadText()
    }
    private fun setupCallbacks(){
        this.buttonsaved.setOnClickListener{
            comment.text = comments.text.toString()
            val t=this.comments.text
            if(t!=null){
                this.savethetext(t.toString())
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clickme)
        comment=findViewById(R.id.textView10)
        comments=findViewById(R.id.notes)
        buttonsaved=findViewById(R.id.bttonsaved)
        this.loadthetext()
        this.setupCallbacks()

        val chatbot=findViewById<Button>(R.id.chatbot)

        chatbot.setOnClickListener {
            val intent= Intent(this,Chatbot::class.java)
            startActivity(intent)
        }
    }
}