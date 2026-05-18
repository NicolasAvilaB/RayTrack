package com.raytrack.data.cache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.raytrack.data.models.Constants.ONBOARDING_DATASTORE

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = ONBOARDING_DATASTORE
)
