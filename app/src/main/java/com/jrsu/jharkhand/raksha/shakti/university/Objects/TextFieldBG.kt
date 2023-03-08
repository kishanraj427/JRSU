package com.jrsu.jharkhand.raksha.shakti.university.Objects

import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun textFieldBG() : TextFieldColors{
    return TextFieldDefaults.textFieldColors(
        backgroundColor = Color.White,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        cursorColor = Color.DarkGray,
        focusedLabelColor = Colors.AppBlue,
        disabledLabelColor = Color.Gray
    )
}