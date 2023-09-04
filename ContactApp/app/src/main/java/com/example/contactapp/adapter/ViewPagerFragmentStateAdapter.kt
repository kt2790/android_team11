package com.example.contactapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.contactapp.ui.fragment.ContactListFragment
import com.example.contactapp.ui.fragment.MyPageFragment



class ViewPagerFragmentStateAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    private var fragments: ArrayList<Fragment> = arrayListOf(ContactListFragment(), MyPageFragment())

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun getFragment(position: Int): Fragment {
        return fragments[position]
    }

}