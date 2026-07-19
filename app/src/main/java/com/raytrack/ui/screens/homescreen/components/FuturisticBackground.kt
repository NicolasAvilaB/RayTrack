package com.raytrack.ui.screens.homescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.raytrack.ui.theme.RayTracColors

@Composable
fun FuturisticBackground() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RayTracColors.Background)
    ) {

        // Glow superior
        Box(
            modifier = Modifier
                .size(800.dp)
                .align(Alignment.TopCenter)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            RayTracColors.PrimaryGlow.copy(alpha = 0.10f),
                            androidx.compose.ui.graphics.Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        // Glow central muy tenue
        Box(
            modifier = Modifier
                .size(500.dp)
                .align(Alignment.Center)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            RayTracColors.PrimaryGlow.copy(alpha = 0.03f),
                            androidx.compose.ui.graphics.Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        // Vignette inferior
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            androidx.compose.ui.graphics.Color.Transparent,
                            RayTracColors.Background.copy(alpha = 0.35f),
                            RayTracColors.Background
                        )
                    )
                )
        )
    }
}