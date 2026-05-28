package com.raytrack.ui.screens.onboardingscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
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
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            OnBoardingContent(
                navToHome = navToHome,
                saveOnBoarding = { viewModel.saveOnBoarding() },
                uiState = uiState
            )
        }
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
