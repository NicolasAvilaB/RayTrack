package com.raytrack.data.repository.maps

import android.location.Location
import com.raytrack.data.maps.LocationProvider
import kotlinx.coroutines.flow.Flow

internal class ObserveLocationUseCase(
    private val locationProvider: LocationProvider
) {
    fun invoke(): Flow<Location> {
        return locationProvider.locationUpdates()
    }
}
