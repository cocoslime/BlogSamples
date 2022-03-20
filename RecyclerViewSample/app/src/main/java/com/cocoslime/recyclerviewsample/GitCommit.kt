package com.cocoslime.recyclerviewsample

data class GitCommit(
    val message : String,
    val author : String,
    val timeStamp : String,
    val hash : String,
    val subMessage : String? = null
)
{
    var showSubMessage = false
}