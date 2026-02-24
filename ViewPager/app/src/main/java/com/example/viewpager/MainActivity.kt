package com.example.viewpager

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity()
{
    lateinit var viewPager: ViewPager2
    lateinit var adapter: MyPagerAdapter
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager2)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        adapter = MyPagerAdapter(supportFragmentManager, lifecycle)

        adapter.addFragmentList(FragmentOne())
        adapter.addFragmentList(FragmentTwo())
        adapter.addFragmentList(FragmentThree())

        viewPager.adapter = adapter

        tabLayout = findViewById(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()

    }
}