package com.example.appchat

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appchat.databinding.ActivityFirstBinding
import com.example.appchat.databinding.ActivityMainBinding

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.go.setOnClickListener {
            if (binding.username.text.toString().isNotEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("username", binding.username.text.toString())
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Please Enter your Name",Toast.LENGTH_SHORT).show()
            }
        }
    }
}