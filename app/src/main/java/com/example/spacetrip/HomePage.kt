package com.example.spacetrip

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val newsbutton = findViewById<CardView>(R.id.newsCard)
        val satellitebutton = findViewById<CardView>(R.id.satelliteCard)
        val launchersbutton = findViewById<CardView>(R.id.launchersCard)
        val chatbotbutton = findViewById<CardView>(R.id.chatbotCard)

        newsbutton.setOnClickListener{
            val intent = Intent(this, NoticeBoard::class.java)
            startActivity(intent)
        }
        satellitebutton.setOnClickListener{
            val intent = Intent(this, SpaceStuff::class.java)
            startActivity(intent)
        }
        launchersbutton.setOnClickListener{
            val intent = Intent(this, RocketList::class.java)
            startActivity(intent)
        }
        chatbotbutton.setOnClickListener{
            val intent = Intent(this, ChatBot::class.java)
            startActivity(intent)
        }
    }
}