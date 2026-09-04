package com.raytrack.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.raytrack.presentation.home.HomeViewModel
import com.raytrack.presentation.maps.MapsViewModel
import com.raytrack.presentation.onboarding.OnBoardingViewModel
import com.raytrack.ui.navigation.NavRoutes.ErrorNav
import com.raytrack.ui.navigation.NavRoutes.OnBoardingNav
import com.raytrack.ui.navigation.extensions.AppStartResolve
import com.raytrack.ui.navigation.extensions.back
import com.raytrack.ui.navigation.extensions.navigateAfterOnBoarding
import com.raytrack.ui.navigation.extensions.navigateTo
import com.raytrack.ui.screens.homescreen.HomeScreen
import com.raytrack.ui.screens.mapscreen.MapScreen
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

    val navigationStack = rememberNavBackStack(startDestination)

    NavDisplay(
        backStack = navigationStack,
        onBack = { navigationStack.back() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<OnBoardingNav> {

                val viewModel: OnBoardingViewModel = koinViewModel() {
                    parametersOf()
                }

                OnBoardingScreen(
                    navToHome = {
                        navigationStack.navigateAfterOnBoarding(NavRoutes.HomeNav)
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
                    onNavToMaps = {
                        navigationStack.navigateTo(NavRoutes.MapsNav)
                    },
                    onSelectRoute = {

                    }
                )
            }
            entry<NavRoutes.MapsNav> { key ->

                val viewModel: MapsViewModel = koinViewModel() {
                    parametersOf()
                }

                MapScreen(
                    viewModel = viewModel,
                    onNavBack = {
                        navigationStack.back()
                    },
                    onNavToAr = {
                        navigationStack.navigateTo(NavRoutes.ArNav)
                    },
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
