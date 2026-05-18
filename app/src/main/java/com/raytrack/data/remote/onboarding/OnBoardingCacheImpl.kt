package com.raytrack.data.remote.onboarding

import com.raytrack.data.cache.OnBoardingDataStore
import kotlinx.coroutines.flow.Flow

internal class OnBoardingCacheImpl(
    private val dataStore: OnBoardingDataStore
) : OnBoardingCacheDataSource {
    override fun isOnBoardingViewed(): Flow<Boolean> {
        return dataStore.isOnBoardingViewed()
    }

    override suspend fun storeOnBoardingView() {
        dataStore.storeOnBoardingView()

    }
}
