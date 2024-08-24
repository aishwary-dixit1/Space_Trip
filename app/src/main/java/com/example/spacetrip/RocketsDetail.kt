package com.example.spacetrip

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RocketsDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rockets_detail)
        val heading = intent.getStringExtra("heading")
        val engineContent = intent.getStringExtra("engineContent")
        val imageId = intent.getIntExtra("imageId", R.drawable.img1)
        val successfullMissions = intent.getStringExtra("successfullMissions")
        val successRate = intent.getStringExtra("successRate")
        val firstLaunch = intent.getStringExtra("firstLaunch")
        val fuelTypes = intent.getStringExtra("fuelTypes")


        val headingTV = findViewById<TextView>(R.id.rocketHeading)
        val headingIV = findViewById<ImageView>(R.id.rocketImage)
        val rocketEngineContentTV = findViewById<TextView>(R.id.rocketEngineContent)
        val rocketFuelTypesTV = findViewById<TextView>(R.id.rocketFuelContent)

        headingTV.text = heading
        rocketEngineContentTV.text = engineContent
        headingIV.setImageResource(imageId)
        rocketFuelTypesTV.text = fuelTypes
    }
}