package com.mehmetpeker.nasaroverproject.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mehmetpeker.nasaroverproject.ui.fragment.CuriosityFragment
import com.mehmetpeker.nasaroverproject.ui.fragment.OpportunityFragment
import com.mehmetpeker.nasaroverproject.ui.fragment.SpiritFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val tabCount = 3
    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment = when(position){
        0 -> CuriosityFragment()
        1 -> OpportunityFragment()
        else -> SpiritFragment()
    }

}