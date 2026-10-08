package com.raytrack.data.repository.maps

import com.raytrack.data.models.MapSearchApiResult

internal interface GeocodingRepository {

    suspend fun search(
        query: String
    ): List<MapSearchApiResult>
}
