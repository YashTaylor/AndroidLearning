package com.example.hiltdependencyinjection

import android.util.Log
import javax.inject.Inject

const val TAG = "DAGGER_HILT"

class UserRepository @Inject constructor()
{
    fun saveUser(email: String, password: String)
    {
        Log.i(TAG, "User saved in DB")
    }
    
}