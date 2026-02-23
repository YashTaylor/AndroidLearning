package com.example.journalapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.journalapp.databinding.ActivitySignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class SignUpActivity : AppCompatActivity()
{
    lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        auth = Firebase.auth

        binding.btnSignUp.setOnClickListener {
            createUser()
        }
    }

    override fun onStart()
    {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null)
        {
            reload()
        }
    }

    private fun createUser()
    {
        val email = binding.tvEmail.text.toString()
        val password = binding.etPassword.text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful)
                {
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "Authentication successful.", Toast.LENGTH_SHORT,).show()
                    updateUI(user)
                } else
                {
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT,).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?)
    {

    }

    private fun reload()
    {

    }

}