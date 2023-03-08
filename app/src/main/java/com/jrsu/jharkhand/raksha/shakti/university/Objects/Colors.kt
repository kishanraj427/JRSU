package com.jrsu.jharkhand.raksha.shakti.university.Objects

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class Colors {
    companion object{
        val AppYellow = Color(0xFFFFDD94) // #FFDD94
        val AppOrange = Color(0xFFFF6A6A) // #FF6A6A
        val AppBlue = Color(0xFF2196f3)   // #2196f3 
        val AppBlack = Color.Gray
        val AppWhite = Color(0xFFF8F9FA)  // #F7F7F7

        val h = Color(0xFFEB5C5C)
        @Composable
        fun SetStatusColor(color: Color){
            val sysUi = rememberSystemUiController()
            sysUi.setSystemBarsColor(
                color = color,
                darkIcons = true
            )
        }

    }
}