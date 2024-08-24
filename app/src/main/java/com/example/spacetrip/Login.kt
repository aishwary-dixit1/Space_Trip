package com.example.spacetrip

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.accessibility.AccessibilityViewCommand.ScrollToPositionArguments
import com.example.spacetrip.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

class Login : AppCompatActivity() {

    private  val  binding: ActivityLoginBinding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }


    lateinit var database : DatabaseReference
    private lateinit var auth : FirebaseAuth
    private lateinit var email : String
    private lateinit var password : String


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        database = Firebase.database.reference
        auth = Firebase.auth


        binding.loginButton.setOnClickListener {
            email = binding.edtEmail.text.toString().trim()

            password = binding.edtPassword.text.toString().trim()
            if( email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Please fill all the details",Toast.LENGTH_SHORT).show()
            }
            else{
                createUserAccount(email,password)
            }

        }

        binding.signuplink.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }



    }

    private fun createUserAccount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                updateUi(user)
                onLoginSuccess()
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser

                        Toast.makeText(this, "CreateUser and Login Successful", Toast.LENGTH_SHORT)
                            .show()
                        saveUserData()
                        updateUi(user)
                    } else {
                        Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                        Log.d("Account", "createUserAccount: Authentication failed", task.exception)
                    }
                }
            }
        }
    }

    private fun saveUserData() {
        email = binding.edtEmail.text.toString().trim()

        password = binding.edtPassword.text.toString().trim()
        val user = UserModel(email,password)
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let {
            database.child("user").child(it).setValue(user)
        }


    }


    private fun readdata(userid: String, password: String){
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(userid).get().addOnSuccessListener {
            if(it.exists()) {
                val userPassword = it.child("password").value
                val userPass = userPassword.toString()
                Log.d(TAG, "Retrieved password: $userPass, Entered password: $password")

                if (password == userPass) {
                    Log.d(TAG, "Passwords match, proceeding to HomePage")

                    Toast.makeText(this, "Succes", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, HomePage::class.java)
                    startActivity(intent)
                } else {
                    Log.d(TAG, "Passwords do not match")
                    Toast.makeText(this, "Enter the correct password", Toast.LENGTH_SHORT).show()
                }
            } else {
                Log.d(TAG, "User does not exist")
                Toast.makeText(this, "User not exists", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Log.e(TAG, "Failed to retrieve user data", it)
            Toast.makeText(this, "User not exists", Toast.LENGTH_SHORT).show()
        }
    }
    fun updateUi(user: FirebaseUser?){
        intent = Intent(this, HomePage::class.java)
        startActivity(intent)
    }
    fun updateUI2(user: FirebaseUser?){
        Toast.makeText(this, "Enter the correct credentials", Toast.LENGTH_LONG).show()

    }
    fun onLoginSuccess() {
        // Clear the input fields
        binding.edtEmail.text?.clear()
        binding.edtPassword.text?.clear()

        // Navigate to the next screen or perform other actions
    }

}

