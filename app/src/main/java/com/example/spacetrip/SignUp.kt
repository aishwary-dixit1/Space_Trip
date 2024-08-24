package com.example.spacetrip

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spacetrip.databinding.ActivitySignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class SignUp : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var email : String
    private lateinit var password : String
    private lateinit var userName : String
    private lateinit var database : DatabaseReference

    private  val  binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        auth = Firebase.auth
        database = Firebase.database.reference
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.SignUpButton.setOnClickListener{
            userName=binding.userName.text.toString().trim()
            email = binding.userEmail.text.toString().trim()
            password = binding.userPassword.text.toString().trim()
            if(userName.isEmpty() || email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Please fill all the details",Toast.LENGTH_SHORT).show()
            }
            else{
                createAccount(email,password)
            }
        }

    }
    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(this, "Account Created",Toast.LENGTH_SHORT).show()
                saveUserData()
                val intent = Intent(this, Login::class.java)
                startActivity(intent)

            }
            else
            {
                Toast.makeText(this , "Failure!", Toast.LENGTH_SHORT).show()
                Log.d("Account","Failure",task.exception)
            }
        }
    }
    private fun saveUserData() {

        userName=binding.userName.text.toString().trim()
        email = binding.userEmail.text.toString().trim()
        password = binding.userPassword.text.toString().trim()
        val user =  UserModel(userName,email,password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        database.child("user").child(userId).setValue(user)
    }

}