package com.example.contactapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contactapp.R
import com.example.contactapp.databinding.FragmentMyPageBinding
import com.example.contactapp.manager.ContactManagerImpl
import com.example.contactapp.model.Contact


class MyPageFragment : Fragment() {

    private lateinit var binding: FragmentMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    val contactManagerImpl : ContactManagerImpl = ContactManagerImpl.getInstance()



        val contactList = contactManagerImpl.getContactList()
        val contact = contactList.first()

        binding.Image.setImageResource(R.drawable.ic_launcher_foreground)
        binding.MyName.text = contact.name
        binding.MobileTX.text = contact.phone
        binding.EventTx.text = contact.alarm.toString()
        binding.EmailTx.text = contact.email
}
}