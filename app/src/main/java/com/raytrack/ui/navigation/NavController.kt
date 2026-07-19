package com.raytrack.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.raytrack.presentation.home.HomeViewModel
import com.raytrack.presentation.onboarding.OnBoardingViewModel
import com.raytrack.ui.navigation.NavRoutes.ErrorNav
import com.raytrack.ui.navigation.NavRoutes.OnBoardingNav
import com.raytrack.ui.navigation.extensions.AppStartResolve
import com.raytrack.ui.navigation.extensions.back
import com.raytrack.ui.navigation.extensions.navigateAfterOnBoarding
import com.raytrack.ui.screens.homescreen.HomeScreen
import com.raytrack.ui.screens.onboardingscreen.OnBoardingScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun NavController(
    viewModel: AppStartResolve = koinViewModel()
) {
    val startDestination = produceState<NavRoutes?>(null) {
        value = viewModel.resolve()
    }.value

    if (startDestination == null) return

    val backStack = rememberNavBackStack(startDestination)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.back() },
        entryProvider = entryProvider {
            entry<OnBoardingNav> {

                val viewModel: OnBoardingViewModel = koinViewModel() {
                    parametersOf()
                }

                OnBoardingScreen(
                    navToHome = {
                        backStack.navigateAfterOnBoarding(NavRoutes.HomeNav)
                    },
                    viewModel = viewModel,
                )
            }
            entry<NavRoutes.HomeNav> { key ->

                val viewModel: HomeViewModel = koinViewModel() {
                    parametersOf()
                }

                HomeScreen(
                    viewModel = viewModel,
                    onSearch = {

                    },
                    onSelectRoute = {

                    }
                )
            }
            entry<ErrorNav> {
                Text("Error")
            }
        },
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
