package com.raytrack.data.repository.onboarding

import kotlinx.coroutines.flow.Flow

internal class OnBoardingUseCase(
    private val repository: OnBoardingRepository
) {
    fun isOnBoardingViewed(): Flow<Boolean> {
        return repository.isOnBoardingViewed()
    }

    suspend fun completeOnBoarding() {
        repository.storeOnBoardingView()
    }
}
