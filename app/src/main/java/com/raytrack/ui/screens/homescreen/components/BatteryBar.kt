package com.raytrack.ui.screens.homescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raytrack.ui.theme.RayTracColors

@Composable
fun BatteryBar(
    level: Int
) {

    Box(
        modifier = Modifier
            .width(42.dp)
            .height(14.dp)
            .border(
                width = 1.dp,
                color = RayTracColors.PrimaryGlow,
                shape = RoundedCornerShape(2.dp)
            )
    ) {

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width((40 * level / 100).dp)
                .background(
                    RayTracColors.PrimaryGlow
                )
                .align(Alignment.CenterStart)
        )
    }
}