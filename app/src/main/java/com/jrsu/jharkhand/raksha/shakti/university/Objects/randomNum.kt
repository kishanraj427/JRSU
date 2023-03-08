package com.jrsu.jharkhand.raksha.shakti.university.Objects

import java.text.SimpleDateFormat
import java.util.*

fun randomNum() : String{
    return SimpleDateFormat("yyyyMMdd_HHmmss", Locale("hi")).format(Date())
}