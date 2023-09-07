package com.example.contactapp.util

class NotificationConvert {
    companion object {
        fun convert(id: Int) = when(id) {
            0 -> "OFF"
            1 -> "5분 뒤 알림"
            2 -> "10분 뒤 알림"
            3 -> "30분 뒤 알림"
            else -> "OFF"
        }
    }
}