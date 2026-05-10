package com.raytrack.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class NavRoutes: NavKey {
    @Serializable
    data object OnBoarding: NavRoutes()
    @Serializable
    data class Home(val id:String): NavRoutes()
    @Serializable
    data object ErrorNav: NavRoutes()
}
