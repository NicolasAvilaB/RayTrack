package com.raytrack.ui.screens.homescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Battery6Bar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raytrack.ui.theme.RayTracColors

@Composable
fun BatteryStatusItem(
    batteryLevel: Int
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = Icons.Outlined.Battery6Bar,
            contentDescription = null,
            tint = RayTracColors.PrimaryGlow
        )

        Spacer(
            modifier = Modifier.width(8.dp)
        )

        Column {

            Text(
                text = "BATERÍA",
                color = RayTracColors.TextSecondary,
                fontSize = 14.sp
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "$batteryLevel%",
                    color = RayTracColors.PrimaryGlow,
                    fontSize = 16.sp
                )
            }
        }
    }
}