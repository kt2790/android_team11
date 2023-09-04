package com.example.contactapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.contactapp.R
import com.example.contactapp.adapter.ViewPagerFragmentStateAdapter
import com.example.contactapp.databinding.FragmentHomeBinding
import com.example.contactapp.ui.dialog.AddContactDialogFragment
import com.google.android.material.tabs.TabLayoutMediator

const val CONTACT_PAGE = 0
const val MY_PAGE = 1

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var title = arrayOf("CONTACT", "MY PAGE")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.vpContact.adapter = ViewPagerFragmentStateAdapter(requireActivity())
        binding.vpContact.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(binding.mainTab, binding.vpContact) { tab, position ->
            tab.text = title[position]
        }.attach()

        binding.btnAddcontact.setOnClickListener {
            val dialog = AddContactDialogFragment()
            dialog.isCancelable = true
            dialog.show(requireActivity().supportFragmentManager, "ConfirmDialog")
        }

        return binding.root
    }

}