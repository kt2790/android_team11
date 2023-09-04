package com.example.contactapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contactapp.R
import com.example.contactapp.databinding.FragmentContactDetailBinding
import com.example.contactapp.manager.ContactManagerImpl

class ContactDetailFragment : Fragment() {

    private var _binding : FragmentContactDetailBinding? = null
    private val binding
        get() = _binding!!
    private var contactManagerImpl: ContactManagerImpl = ContactManagerImpl.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeView() {
        val contact = contactManagerImpl.getContactById(0)!!

        binding.detailName.text = contact.name
        binding.detailEventState.text = contact.alarm.toString()
        binding.detailPhoneNumber.text = contact.phone
        binding.detailEmailText.text = contact.email
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}