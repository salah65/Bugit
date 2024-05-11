package com.example.bugit.features.home_screen.presentation.core

import java.text.SimpleDateFormat
import java.util.Date

fun Date.getCurrentDateFormatted(): String {
    val currentDate = this
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    val formattedDate = dateFormat.format(currentDate)
    return formattedDate
}