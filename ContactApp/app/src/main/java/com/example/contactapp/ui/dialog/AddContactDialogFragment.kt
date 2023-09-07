package com.example.contactapp.ui.dialog

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.DialogFragment
import com.example.contactapp.R
import com.example.contactapp.adapter.ContactAdapter
import com.example.contactapp.databinding.FragmentAddContactDialogBinding
import com.example.contactapp.manager.ContactManagerImpl
import com.example.contactapp.ui.activity.MainActivity
import com.example.contactapp.ui.fragment.ContactListFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class AddContactDialogFragment(private val contactListFragment: ContactListFragment) : DialogFragment() {

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

        binding.offbtn.setOnClickListener {
            setButtonState(binding.offbtn)
            alarmNumber = 0
        }

        binding.fivebtn.setOnClickListener {
            setButtonState(binding.fivebtn)
            alarmNumber = 1
        }

        binding.tenBtn.setOnClickListener {
            setButtonState(binding.tenBtn)
            alarmNumber = 2
        }

        binding.thirtyBtn.setOnClickListener {
            setButtonState(binding.thirtyBtn)
            alarmNumber = 3
        }

        binding.saveBtn.setOnClickListener {
            if (alarmNumber != 0) {
                GlobalScope.launch {
                    when (alarmNumber) {
                        1 -> delay(5 * 1000)
                        2 -> delay(10 * 60 * 1000)
                        3 -> delay(30 * 60 * 1000)
                    }
                    notification()
                }
            }
            // EditText창에 입력된 텍스트를 가져옴
            val name = binding.NameEdit.text.toString()
            val phoneNumber = binding.NumberEdit.text.toString()
            val email = binding.EmailEdit.text.toString()
            val alarm = 0


            // ContactManagerImpl 클래스의 싱글톤 객체를 가져옴
            val contactManagerImpl: ContactManagerImpl = ContactManagerImpl.getInstance()
            // 가져온 객체를 이용해서 새로운 데이터 생성
            contactManagerImpl.createContact(name, phoneNumber, email, alarm)

            val updateContactList = contactManagerImpl.getContactList()
            contactListFragment.adapter.setContactList(updateContactList)
            dismiss()
        }
    }

    fun notification() {
        val manager = binding.root.context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder
        val name = binding.NameEdit.text.toString()


        // SDK 26 version 이상
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "one_channel"
            val channelNAme = "My channer one"
            val channel = NotificationChannel(
                channelId,
                channelNAme,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "My Channel One Description"
                setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM).build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }
            manager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(binding.root.context, channelId)
        } else {
            builder = NotificationCompat.Builder(binding.root.context)
        }

        // 알림 클릭 시 앱으로 이동
        val intent = Intent(binding.root.context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(binding.root.context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE )


        builder.run {
            setSmallIcon(R.drawable.ic_launcher_foreground)
            setWhen(System.currentTimeMillis())
            setContentTitle("새로운 알림입니다.")
            setContentText("$name 님에게 연락할 시간입니다.")
            setContentIntent(pendingIntent)
            setAutoCancel(true)
        }

        manager.notify(11, builder.build())
    }


    // btn 클릭 시 색상 변경
    private fun setButtonState(selectedButton: Button) {
        binding.offbtn.setBackgroundColor(Color.parseColor("#FFC0C2C3"))

        binding.fivebtn.setBackgroundColor(Color.parseColor("#FFC0C2C3"))

        binding.tenBtn.setBackgroundColor(Color.parseColor("#FFC0C2C3"))

        binding.thirtyBtn.setBackgroundColor(Color.parseColor("#FFC0C2C3"))

        selectedButton.setBackgroundColor(Color.parseColor("#FF499EE3"))
        selectedButton.setTextColor(Color.parseColor("#FFE9E5E5"))
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

