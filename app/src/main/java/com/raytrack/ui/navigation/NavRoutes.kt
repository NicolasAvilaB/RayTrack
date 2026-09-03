package com.raytrack.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class NavRoutes: NavKey {
    @Serializable
    data object OnBoardingNav: NavRoutes()
    @Serializable
    data object HomeNav: NavRoutes()
    @Serializable
    data object MapsNav: NavRoutes()
    @Serializable
    data object ArNav: NavRoutes()
    @Serializable
    data object ErrorNav: NavRoutes()
}
