package com.example.androidlearning

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadCastReceiver : BroadcastReceiver()
{
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action != null && intent.action!!.equals(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        {
            val airplaneMode = intent.getBooleanExtra("state", false)
            if (airplaneMode) Toast.makeText(context, "Airplane mode is ON", Toast.LENGTH_LONG).show()
            else Toast.makeText(context, "Airplane mode is OFF", Toast.LENGTH_LONG).show()
        }
    }

}