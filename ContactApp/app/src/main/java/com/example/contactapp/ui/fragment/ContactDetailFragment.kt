package com.example.contactapp.ui.fragment

import android.content.Intent
import android.net.Uri
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
    var itemId = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactDetailBinding.inflate(inflater, container, false)
        itemId = arguments?.getInt("ITEM_ID") ?: 1

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeView() {
        val contact = contactManagerImpl.getContactById(itemId)!!

        binding.detailName.text = contact.name
        binding.detailEventState.text = contact.alarm.toString()
        binding.detailPhoneNumber.text = contact.phone
        binding.detailEmailText.text = contact.email
        binding.detailProfileImg.setImageResource(resources.getIdentifier(contact.profile, "drawable", binding.root.context.packageName))

        binding.detailMsgBtn.setOnClickListener {
            val smsUri = Uri.parse("smsto:" + contact.phone)
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = smsUri
            intent.putExtra("sms_body", "")
            startActivity(intent)
        }

        binding.detailCallBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contact.phone))
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}