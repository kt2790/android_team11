package com.example.contactapp.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.contactapp.R
import com.example.contactapp.databinding.FragmentConfirmDialogBinding
import com.example.contactapp.model.Contact

class ConfirmDialog(
    contact: Contact, contactDeleteListener: ContactDeleteListener
) : DialogFragment() {

    private var _binding: FragmentConfirmDialogBinding? = null
    private val binding get() = _binding!!

    private var contactDeleteListener : ContactDeleteListener
    private var contact : Contact

    init {
        this.contactDeleteListener = contactDeleteListener
        this.contact = contact
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.confirmTextView.text = "연락처를 정말로 삭제하시겠습니까?"

        binding.noButton.setOnClickListener {
            dismiss()
        }

        binding.yesButton.setOnClickListener {
            contactDeleteListener.contactDeleteListener(contact)
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}