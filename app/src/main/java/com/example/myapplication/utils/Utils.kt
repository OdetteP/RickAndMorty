package com.example.myapplication.utils

import androidx.compose.ui.graphics.Color
import com.example.myapplication.ui.theme.*
import java.util.*

fun parseStatusToColor(status: String): Color {
    return when (status.toLowerCase(Locale.ROOT)) {
        "alive" -> AliveColor
        "dead" -> DeadColor
        "human" -> HumanColor
        "alien" -> AlienColor
        else -> UnknownColor
    }
}