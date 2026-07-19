package com.raytrack.ui.screens.homescreen.model

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween

object RayTracMotion {

    val screenEnter = tween<Float>(
        durationMillis = 700,
        easing = FastOutSlowInEasing
    )

    val cardEnter = tween<Float>(
        durationMillis = 450,
        easing = FastOutSlowInEasing
    )

    val searchFocus = tween<Float>(
        durationMillis = 250,
        easing = FastOutSlowInEasing
    )

    val radarPulse = tween<Float>(
        durationMillis = 2200,
        easing = FastOutSlowInEasing
    )

    val radarRotation = tween<Float>(
        durationMillis = 9000,
        easing = LinearEasing
    )
}