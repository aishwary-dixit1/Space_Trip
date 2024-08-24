package com.example.spacetrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ChatBot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bot)
    }

    val apiKey = com.example.spacetrip.BuildConfig.Google_API_KEY
    public  fun GeminiAPI(view:View){
        var textv = findViewById<TextView>(R.id.textView)
        var edit = findViewById<EditText>(R.id.editTextText)
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-flash",
            // Access your API key as a Build Configuration variable (see "Set up your API key" above)
            apiKey = apiKey
        )

//        val prompt = "Write a story about an AI and magic"
        val prompt = edit.text.toString()


        MainScope().launch { val response = generativeModel.generateContent(prompt)
            textv.setText(response.text)
        }

    }
}



