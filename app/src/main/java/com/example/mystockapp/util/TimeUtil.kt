package com.example.mystockapp.util

import java.text.SimpleDateFormat
import java.util.*


fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("yyyy/MM/dd HH:mm a", Locale.US)
    return format.format(date)
}