package com.raytrack.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raytrack.data.remote.onboarding.OnBoardingUseCase
import com.raytrack.presentation.onboarding.OnBoardingUiState.DisplayUiState
import com.raytrack.presentation.onboarding.OnBoardingUiState.ErrorUiState
import com.raytrack.presentation.onboarding.OnBoardingUiState.LoadingUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class OnBoardingViewModel(
    private val useCase: OnBoardingUseCase
) : ViewModel() {

    val uiState: StateFlow<OnBoardingUiState> =
        useCase.isOnBoardingViewed()
            .map<Boolean, OnBoardingUiState> {
                DisplayUiState
            }
            .onStart {
                emit(LoadingUiState)
            }
            .catch {
                emit(ErrorUiState)
            }
            .distinctUntilChanged()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(0),
                initialValue = LoadingUiState
            )

    fun saveOnBoarding() {
        viewModelScope.launch {
            useCase.completeOnBoarding()
        }
    }
}
