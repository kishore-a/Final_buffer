package com.example.finalproject

import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    var name=""
    fun updateName(s:String){
        name=s
    }
}