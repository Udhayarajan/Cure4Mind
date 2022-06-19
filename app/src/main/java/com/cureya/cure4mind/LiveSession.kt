package com.cureya.cure4mind

import java.text.SimpleDateFormat
import java.util.*

data class LiveSession(
    val sessionId: Int,
    val sessionName:String,
    val hostName: String,
    val sessionDate: Date,
    val sessionDescription: String,
){
    fun getDate():String{
        val date = sessionDate
        val dateFormat = SimpleDateFormat("dd/MM/yyyy, hh:mm a", Locale.getDefault())
        return dateFormat.format(date)
    }
}
