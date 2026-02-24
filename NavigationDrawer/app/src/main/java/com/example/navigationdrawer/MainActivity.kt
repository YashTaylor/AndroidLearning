package com.example.navigationdrawer

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener
{
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true)
        {
            override fun handleOnBackPressed()
            {
                if(drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.closeDrawer(GravityCompat.START)
                else finish()
            }

        })

        drawerLayout = findViewById(R.id.drawerLayout)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null)
        {
            replaceFragment(FragmentHome())
            navigationView.setCheckedItem(R.id.nav_home)
        }
    }

    fun replaceFragment(fragment: Fragment)
    {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, fragment)
        ft.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId)
        {
            R.id.nav_home -> {
                replaceFragment(FragmentHome())
            }
            R.id.nav_settings -> {
                replaceFragment(FragmentSettings())
            }
            R.id.nav_share -> {
                replaceFragment(FragmentShare())
            }
            R.id.nav_about_us -> {
                replaceFragment(FragmentAboutUs())
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}