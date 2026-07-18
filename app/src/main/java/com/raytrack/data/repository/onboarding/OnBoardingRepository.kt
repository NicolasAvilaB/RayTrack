package com.raytrack.data.repository.onboarding

import kotlinx.coroutines.flow.Flow

internal interface OnBoardingRepository {

    fun isOnBoardingViewed(): Flow<Boolean>

    suspend fun storeOnBoardingView()
}
