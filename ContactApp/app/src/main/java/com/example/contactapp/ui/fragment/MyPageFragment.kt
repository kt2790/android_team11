package com.example.contactapp.ui.fragment

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contactapp.R
import com.example.contactapp.databinding.FragmentMyPageBinding
import com.example.contactapp.manager.ContactManagerImpl
import com.example.contactapp.model.Contact
import com.example.contactapp.ui.activity.MainActivity
import com.example.contactapp.util.NotificationConvert
import java.util.Locale


class MyPageFragment : Fragment() {

    private lateinit var binding: FragmentMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).hideToolbar()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).hideToolbar()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    val contactManagerImpl : ContactManagerImpl = ContactManagerImpl.getInstance()



        val contactList = contactManagerImpl.getContactList()
        val contact = contactList.first()

        binding.Image.setImageResource(R.drawable.pepe9)
        binding.MyName.text = contact.name
        binding.MobileTX.text = PhoneNumberUtils.formatNumber(contact.phone, Locale.getDefault().country)
        binding.EventTx.text = NotificationConvert.convert(contact.alarm)
        binding.EmailTx.text = contact.email
}
}