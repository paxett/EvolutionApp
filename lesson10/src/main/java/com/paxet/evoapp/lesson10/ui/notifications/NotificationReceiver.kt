package com.paxet.evoapp.lesson10.ui.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val duration = when (intent.action) {
            ACTION_TOAST_SHORT -> Toast.LENGTH_SHORT
            ACTION_TOAST_LONG -> Toast.LENGTH_LONG
            else -> throw IllegalArgumentException("Unexpected intent action")
        }
        val message = intent.getStringExtra(ARG_MESSAGE)
        Toast.makeText(context, message, duration).show()
    }

    companion object {
        const val ACTION_TOAST_LONG = "ACTION_TOAST_LONG"
        const val ACTION_TOAST_SHORT = "ACTION_TOAST_SHORT"
        const val ARG_MESSAGE = "ARG_MESSAGE"
    }

}

