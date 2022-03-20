package com.cocoslime.recyclerviewsample

import java.text.SimpleDateFormat
import java.util.*

fun String.toCommitDate() : Date {
    return SimpleDateFormat("E MMM dd HH:mm:ss yyyy Z", Locale.ENGLISH).parse(this)!!
}