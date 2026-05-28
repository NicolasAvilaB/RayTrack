package com.raytrack.data.remote.onboarding

import kotlinx.coroutines.flow.Flow

internal class OnBoardingUseCase(
    private val sources: OnBoardingCacheDataSource
) {
    fun isOnBoardingViewed(): Flow<Boolean> {
        return sources.isOnBoardingViewed()
    }

    suspend fun completeOnBoarding() {
        sources.storeOnBoardingView()
    }
}
