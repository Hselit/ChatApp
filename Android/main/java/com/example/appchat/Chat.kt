package com.example.appchat

data class Chat(
    val username:String,
    val text:String,
    var isSelf: Boolean = false
)
