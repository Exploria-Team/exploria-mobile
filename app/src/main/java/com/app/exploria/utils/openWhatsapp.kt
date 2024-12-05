package com.app.exploria.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

fun openWhatsApp(context: Context, phoneNumber: String) {
    try {
        val url = "https://wa.me/$phoneNumber"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.putExtra(Intent.EXTRA_TEXT, "Hi, I would like to inquire about your tour services.")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
