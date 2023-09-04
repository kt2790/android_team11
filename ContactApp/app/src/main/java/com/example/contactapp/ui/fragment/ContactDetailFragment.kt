package com.example.contactapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contactapp.R
import com.example.contactapp.databinding.FragmentContactDetailBinding

class ContactDetailFragment : Fragment() {

    private lateinit var binding : FragmentContactDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactDetailBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_contact_detail, container, false)
    }
}