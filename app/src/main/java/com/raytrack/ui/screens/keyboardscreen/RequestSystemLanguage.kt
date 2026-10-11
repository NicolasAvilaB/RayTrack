package com.raytrack.ui.screens.keyboardscreen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.os.ConfigurationCompat

@Composable
internal fun rememberSystemLanguage(): String {
    val context = LocalContext.current
    val language = remember {
        mutableStateOf(readSystemLanguage())
    }

    DisposableEffect(context) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(
                context: Context?,
                intent: Intent?
            ) {
                language.value = readSystemLanguage()
            }
        }

        ContextCompat.registerReceiver(
            context,
            receiver,
            IntentFilter(Intent.ACTION_LOCALE_CHANGED),
            ContextCompat.RECEIVER_EXPORTED
        )

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }

    return language.value
}

private fun readSystemLanguage(): String {
    val locales = ConfigurationCompat.getLocales(
        Resources.getSystem().configuration
    )

    return locales[0]?.language ?: "en"
}
