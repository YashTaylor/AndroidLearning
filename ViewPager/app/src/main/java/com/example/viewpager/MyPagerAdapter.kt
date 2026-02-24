package com.example.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyPagerAdapter(fm: FragmentManager, lc: Lifecycle) : FragmentStateAdapter(fm, lc)
{

    var fragArray: ArrayList<Fragment> = ArrayList()

    fun addFragmentList(fragment: Fragment)
    {
        fragArray.add(fragment)
    }

    override fun createFragment(position: Int): Fragment
    {
        return fragArray.get(position)
    }

    override fun getItemCount(): Int
    {
        return fragArray.size
    }

}