package com.raytrack.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raytrack.data.remote.onboarding.OnBoardingRepository
import com.raytrack.presentation.onboarding.OnBoardingUiState.DisplayUiState
import com.raytrack.presentation.onboarding.OnBoardingUiState.ErrorUiState
import com.raytrack.presentation.onboarding.OnBoardingUiState.LoadingUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class OnBoardingViewModel(
    private val repository: OnBoardingRepository
) : ViewModel() {

    val defaultUiState = LoadingUiState

    fun loadOnBoarding(): Flow<OnBoardingUiState> =
        repository.isOnBoardingViewed()
            .map<Boolean, OnBoardingUiState> { viewed ->
                DisplayUiState
            }
            .onStart {
                emit(LoadingUiState)
            }
            .catch {
                emit(ErrorUiState)
            }
            .distinctUntilChanged()

    fun saveOnBoarding() {
        viewModelScope.launch {
            repository.completeOnBoarding()
        }
    }
}
