package com.raytrack.data.cache

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class OnBoardingDataStore(
    private val dataStore: DataStore<Preferences>
) {
    private object Keys {
        val IS_VIEWED_ONBOARDING = booleanPreferencesKey("IS_VIEWED_ONBOARDING")
    }

    suspend fun storeOnBoardingView() {
        dataStore.edit { preferences ->
            preferences[Keys.IS_VIEWED_ONBOARDING] = true
        }
    }

    fun isOnBoardingViewed(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[Keys.IS_VIEWED_ONBOARDING] ?: false
        }
    }
}
