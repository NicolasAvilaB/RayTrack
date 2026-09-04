package com.raytrack.ui.screens.mapscreen.stateview

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raytrack.ui.screens.homescreen.components.FuturisticBackground
import com.raytrack.ui.theme.RayTracColors

@Composable
internal fun LoadingMapView(visible: Boolean) {
    val infiniteTransition = rememberInfiniteTransition(
        label = "loading"
    )

    val glowScale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.85f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = EaseInOut
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow_scale"
    )

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.18f,
        targetValue = 0.75f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = EaseInOut
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow_alpha"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        RayTracColors.PrimaryGlow.copy(alpha = 0.18f),
                        RayTracColors.PrimaryGlow.copy(alpha = 0.06f),
                        Color.Transparent
                    )
                )
            )
    ) {

        FuturisticBackground()

        // Central pulsating glow
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .scale(glowScale)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            RayTracColors.PrimaryGlow.copy(
                                alpha = glowAlpha
                            ),
                            RayTracColors.PrimaryGlow.copy(
                                alpha = glowAlpha * 0.35f
                            ),
                            RayTracColors.PrimaryGlow.copy(
                                alpha = 0f
                            )
                        )
                    )
                )
        )

        // Content
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            Text(
                text = "INITIALIZING CARTOGRAPHY",
                color = RayTracColors.PrimaryWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 2.sp
            )

            Text(
                text = "SYNCING NAVIGATION DATA",
                color = RayTracColors.TextSecondary,
                fontSize = 14.sp,
                letterSpacing = 1.5.sp
            )
        }
    }
}


@Preview
@Composable
fun LoadingMapViewPreview() {
    LoadingMapView(true)
}