package com.example.finalproject

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Constants.OPEN_GOOGLE
import com.example.finalproject.Constants.OPEN_SEARCH
import com.example.finalproject.Constants.RECEIVE_ID
import com.example.finalproject.Constants.SEND_ID
import kotlinx.coroutines.*

class Chatbot : AppCompatActivity() {
    var messagesList = mutableListOf<Message>()

    private lateinit var adapter: MessagingAdapter
    private val botList = listOf("Peter", "Francesca", "Luigi", "Igor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatbot)
        recyclerView()

        clickEvents()

        val random = (0..3).random()
        customBotMessage("Hello! Today you're speaking with ${botList[random]}, how may I help?")
    }

    private fun clickEvents() {
        val btn_send=findViewById<Button>(R.id.btn_send)
        val et_message=findViewById<EditText>(R.id.et_message)
        val rv_messages=findViewById<RecyclerView>(R.id.rv_messages)


        btn_send.setOnClickListener {
            sendMessage()
        }


        et_message.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {

                    rv_messages.scrollToPosition(adapter.itemCount - 1)

                }
            }
        }
    }
    private fun recyclerView() {
        val rv_messages=findViewById<RecyclerView>(R.id.rv_messages)

        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)

    }
    override fun onStart() {
        val rv_messages=findViewById<RecyclerView>(R.id.rv_messages)

        super.onStart()
        //In case there are messages, scroll to bottom when re-opening app
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
    private fun sendMessage() {
        val rv_messages=findViewById<RecyclerView>(R.id.rv_messages)

        val et_message=findViewById<EditText>(R.id.et_message)

        val message = et_message.text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            //Adds it to our local list
            messagesList.add(Message(message, SEND_ID, timeStamp))
            et_message.setText("")

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            rv_messages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }
    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()

        GlobalScope.launch {

            delay(1000)

            withContext(Dispatchers.Main) {

                val response = BotResponse.basicResponses(message)
                val rv_messages=findViewById<RecyclerView>(R.id.rv_messages)


                messagesList.add(Message(response, RECEIVE_ID, timeStamp))


                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))


                rv_messages.scrollToPosition(adapter.itemCount - 1)


                when (response) {
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }
                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfterLast("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }

                }
            }
        }
    }
    private fun customBotMessage(message: String) {
        val rv_messages=findViewById<RecyclerView>(R.id.rv_messages)


        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                messagesList.add(Message(message, RECEIVE_ID, timeStamp))
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))

                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
}