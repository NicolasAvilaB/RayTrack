package com.raytrack.presentation.onboarding

internal sealed class OnBoardingUiState {
    data object LoadingUiState : OnBoardingUiState()
    data object DisplayUiState : OnBoardingUiState()
    data object ErrorUiState : OnBoardingUiState()
}