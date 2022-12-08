package com.example.finalproject

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView

class VideoDescription : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_description)
        val mediaController= MediaController(this)
        val videView=findViewById<VideoView>(R.id.video)
        mediaController.setAnchorView(videView)
        val offilineUri=  Uri.parse("android.resource://$packageName/${R.raw.vid}")
        videView.setMediaController(mediaController)
        videView.setVideoURI(offilineUri)
        videView.start()
    }
}