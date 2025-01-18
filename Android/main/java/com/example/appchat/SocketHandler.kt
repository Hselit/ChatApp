package com.example.appchat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket

class SocketHandler {

    private var socket:Socket? = null
    private val chatMessage = MutableLiveData<Chat>()
    val newChatMessage:LiveData<Chat> get() = chatMessage


    private companion object{
        private const val SOCKET_URI="http://192.168.233.146:3000/"
    }

    init {
        try {
            socket = IO.socket(SOCKET_URI)
            socket?.on(Socket.EVENT_CONNECT) {
                println("Socket connected")
            }
            socket?.on(Socket.EVENT_CONNECT_ERROR) { args ->
                println("Connection error: ${args[0]}")
            }
            socket?.on(Socket.EVENT_DISCONNECT) {
                println("Socket disconnected")
            }
            socket?.connect()
            registerOnNewChat()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun disconnectSocket(){
        socket?.disconnect()
        socket?.off()
    }

    fun emitMessage(chat: Chat){
        println("inside emit message "+chat)
        val jsonstr = Gson().toJson(chat,Chat::class.java)
        socket?.emit("New_Message",jsonstr)
        println("message emmitted")
    }

    fun registerOnNewChat(){
        socket?.on(CHAT_KEYS.newMessage){args->
            args?.let { data ->
                if (data.toString().isNotEmpty()){
                    val chat = Gson().fromJson(data.toString(),Chat::class.java)
                    chatMessage.postValue(chat)
                }
            }
        }
    }

    private object CHAT_KEYS{
        const val newMessage = "New_Message"
    }
}