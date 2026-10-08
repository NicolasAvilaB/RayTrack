package com.raytrack.data.repository.maps

import com.raytrack.data.models.MapSearchApiResult

internal class GeocodingSearchLocationUseCase(
    private val geocodingRepository: GeocodingRepository
) {

    suspend operator fun invoke(
        query: String
    ): List<MapSearchApiResult> {
        return geocodingRepository.search(query)
    }
}
