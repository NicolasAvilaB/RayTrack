package com.raytrack.ui.screens.mapscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raytrack.ui.theme.RayTracColors

@Composable
internal fun CurrentLocationIndicator(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.size(28.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(
                    color = RayTracColors.PrimaryGlow.copy(alpha = 0.12f),
                    shape = CircleShape
                )
        )

        Box(
            modifier = Modifier
                .size(16.dp)
                .background(
                    color = RayTracColors.PrimaryGlow.copy(alpha = 0.25f),
                    shape = CircleShape
                )
                .border(
                    width = 1.dp,
                    color = RayTracColors.PrimaryGlow,
                    shape = CircleShape
                )
        )

        Box(
            modifier = Modifier
                .size(6.dp)
                .background(
                    color = RayTracColors.PrimaryWhite,
                    shape = CircleShape
                )
        )
    }
}