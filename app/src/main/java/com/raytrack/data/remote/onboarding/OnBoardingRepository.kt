package com.raytrack.data.remote.onboarding

import kotlinx.coroutines.flow.Flow

internal class OnBoardingRepository(
    private val cacheSource: OnBoardingCacheDataSource
) {
    fun isOnBoardingViewed(): Flow<Boolean> {
        return cacheSource.isOnBoardingViewed()
    }

    suspend fun completeOnBoarding() {
        cacheSource.storeOnBoardingView()
    }
}