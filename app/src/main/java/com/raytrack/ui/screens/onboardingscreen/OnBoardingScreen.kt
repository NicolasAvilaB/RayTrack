package com.raytrack.ui.screens.onboardingscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raytrack.presentation.onboarding.OnBoardingUiState
import com.raytrack.presentation.onboarding.OnBoardingUiState.DisplayUiState
import com.raytrack.presentation.onboarding.OnBoardingUiState.ErrorUiState
import com.raytrack.presentation.onboarding.OnBoardingUiState.LoadingUiState
import com.raytrack.presentation.onboarding.OnBoardingViewModel
import com.raytrack.ui.screens.onboardingscreen.stateview.DisplayOnBoardingView
import com.raytrack.ui.screens.onboardingscreen.stateview.ErrorOnBoardingView
import com.raytrack.ui.screens.onboardingscreen.stateview.LoadingOnBoardingView

@Composable
internal fun OnBoardingScreen(
    viewModel: OnBoardingViewModel,
    navToHome: () -> Unit
) {
    val uiState by remember() {
        viewModel.loadOnBoarding()
    }.collectAsStateWithLifecycle(viewModel.defaultUiState)

    Column {
        OnBoardingContent(
            navToHome = navToHome,
            saveOnBoarding = { viewModel.saveOnBoarding() },
            uiState = uiState
        )
    }

}

@Composable
private fun OnBoardingContent(
    navToHome: () -> Unit,
    saveOnBoarding: () -> Unit,
    uiState: OnBoardingUiState,
) {
    when (uiState) {
        is DisplayUiState -> {
            DisplayOnBoardingView(
                navToHome = navToHome,
                saveOnBoarding = saveOnBoarding,
            )
        }

        is ErrorUiState -> ErrorOnBoardingView()
        is LoadingUiState -> LoadingOnBoardingView()
    }
}
