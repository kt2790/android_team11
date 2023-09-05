package com.example.contactapp.ui.dialog

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.contactapp.R
import com.example.contactapp.databinding.FragmentAddContactDialogBinding
import com.example.contactapp.manager.ContactManagerImpl


class AddContactDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentAddContactDialogBinding
    private var alarmNumber: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddContactDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancelBtn.setOnClickListener {
            dismiss()
        }

        binding.saveBtn.setOnClickListener {

            // EditText창에 입력된 텍스트를 가져옴
            val name = binding.NameEdit.text.toString()
            val phoneNumber = binding.NumberEdit.text.toString()
            val email = binding.EmailEdit.text.toString()
            val alarm = 0

            // ContactManagerImpl 클래스의 싱글톤 객체를 가져옴
            val contactManagerImpl: ContactManagerImpl = ContactManagerImpl.getInstance()
            // 가져온 객체를 이용해서 새로운 데이터 생성
            contactManagerImpl.createContact(name, phoneNumber, email, alarm)

            dismiss()
        }

        binding.offbtn.setOnClickListener {
            setButtonState(binding.offbtn)
            alarmNumber = 0
        }

        binding.tenBtn.setOnClickListener {
            setButtonState(binding.tenBtn)
            alarmNumber = 1
        }

        binding.fivebtn.setOnClickListener {
            setButtonState(binding.fivebtn)
            alarmNumber = 2
        }

        binding.thirtyBtn.setOnClickListener {
            setButtonState(binding.thirtyBtn)
            alarmNumber = 3
        }
    }

    private fun setButtonState(selectedButton: Button) {
        binding.offbtn.setBackgroundColor(Color.parseColor("#D8BFD8"))
        binding.fivebtn.setBackgroundColor(Color.parseColor("#D8BFD8"))
        binding.tenBtn.setBackgroundColor(Color.parseColor("#D8BFD8"))
        binding.thirtyBtn.setBackgroundColor(Color.parseColor("#D8BFD8"))

        selectedButton.setBackgroundColor(Color.parseColor("#E4DA76D5"))
    }

    // dialog size 조절
    override fun onStart() {
        super.onStart()

        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT

            dialog.window?.setLayout(width, height)
        }
    }
}

