package com.cocoslime.recyclerviewsample

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("commitHash")
fun TextView.setCommitHash(item: GitCommit?) {
    item?.let {
        text = item.hash.substring(0, 6)
    }
}

@BindingAdapter("commitTitle")
fun TextView.setCommitTitle(item: GitCommit?) {
    item?.let {
        if (item.message.length >= 25) {
            text = item.message.substring(0, 25) + "..."
        }
    }
}

@BindingAdapter("commitSubMessage")
fun TextView.setCommitSubMessage(item: GitCommit?) {
    item?.let {
        text = item.subMessage
    }
}


fun Date.toCommitString() : String {
    return SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).format(this)
}

@BindingAdapter("commitAuthorTimeStamp")
fun TextView.setCommitAuthorTimeStamp(item: GitCommit?) {
    item?.let {
        text = "${item.author} commited on ${item.timeStamp.toCommitDate().toCommitString()}"
    }
}