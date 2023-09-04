package com.example.contactapp.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.contactapp.R
import com.example.contactapp.databinding.FragmentAddContactDialogBinding


class AddContactDialogFragment : DialogFragment() {

    private lateinit var binding : FragmentAddContactDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddContactDialogBinding.inflate(inflater, container, false)
        return binding.root
    }


}