package com.raytrack.data.remote.onboarding

import kotlinx.coroutines.flow.Flow

internal interface OnBoardingCacheDataSource {

    fun isOnBoardingViewed(): Flow<Boolean>

    suspend fun storeOnBoardingView()
}
