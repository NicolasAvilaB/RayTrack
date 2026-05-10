package com.raytrack.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.raytrack.ui.navigation.NavRoutes.ErrorNav
import com.raytrack.ui.navigation.NavRoutes.Home
import com.raytrack.ui.navigation.NavRoutes.OnBoarding
import com.raytrack.ui.screens.HomeScreen
import com.raytrack.ui.screens.OnBoardingScreen

@Composable
internal fun NavController(
    modifier: Modifier
) {
    val backStack = rememberNavBackStack(OnBoarding)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.back() },
        entryProvider = entryProvider {
            entry<OnBoarding> {
                OnBoardingScreen(
                    navToHome = {
                        backStack.navigateTo(Home(it))
                    }
                )
            }
            entry<Home> { key ->
                HomeScreen(
                    returnId = key.id,
                    navToBack = {
                        backStack.back()
                    }
                )
            }
            entry<ErrorNav> {
                Text("Error")
            }
        },
        modifier = modifier,
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(250)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(250)
            )
        },
        popTransitionSpec = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(250)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(250)
            )
        }
    )
}
