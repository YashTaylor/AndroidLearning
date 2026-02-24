package com.example.androidlearning

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.androidlearning.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{

    var airplaneReceiver : MyBroadCastReceiver? = null

    private lateinit var binder: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)

        binder.btnStart.setOnClickListener {
            startService(Intent(this, MyService::class.java))
        }
        binder.btnStop.setOnClickListener {
            stopService(Intent(this, MyService::class.java))
        }

        val intentFilter = IntentFilter("android.intent.action.AIRPLANE_MODE")
        airplaneReceiver = MyBroadCastReceiver()
        registerReceiver(airplaneReceiver, intentFilter)

        binder.btnFrag1.setOnClickListener {
            loadFragment(FragmentOne())
        }

        binder.btnFrag2.setOnClickListener {
            loadFragment(FragmentTwo())
        }
    }

    fun loadFragment(fragment: Fragment)
    {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fragmentContainer, fragment)
        ft.commit()
    }

    override fun onDestroy()
    {
        unregisterReceiver(airplaneReceiver)
        super.onDestroy()
    }
}