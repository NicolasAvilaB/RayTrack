package com.raytrack.data.repository.onboarding

import com.raytrack.data.cache.OnBoardingDataStore
import kotlinx.coroutines.flow.Flow

internal class OnBoardingImpl(
    private val dataStore: OnBoardingDataStore
) : OnBoardingRepository {

    override fun isOnBoardingViewed(): Flow<Boolean> {
        return dataStore.isOnBoardingViewed()
    }

    override suspend fun storeOnBoardingView() {
        dataStore.storeOnBoardingView()

    }
}
