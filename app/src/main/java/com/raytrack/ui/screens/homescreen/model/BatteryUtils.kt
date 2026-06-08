package com.raytrack.ui.screens.homescreen.model

import android.content.Context
import android.os.BatteryManager

internal object BatteryUtils {

    fun getBatteryLevel(context: Context): Int {
        val batteryManager =
            context.getSystemService(
                Context.BATTERY_SERVICE
            ) as BatteryManager

        return batteryManager.getIntProperty(
            BatteryManager.BATTERY_PROPERTY_CAPACITY
        )
    }
}
