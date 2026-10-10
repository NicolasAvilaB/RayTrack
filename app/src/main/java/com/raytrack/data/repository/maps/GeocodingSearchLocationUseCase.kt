package com.raytrack.data.repository.maps

import com.raytrack.presentation.maps.model.MapSearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class GeocodingSearchLocationUseCase(
    private val geocodingRepository: GeocodingRepository
) {

    internal fun invoke(
        query: String
    ): Flow<List<MapSearchResult>> = flow {
        emit(
            geocodingRepository.search(query)
                .map { apiResults ->
                    MapSearchResult(
                        title = apiResults.title,
                        address = apiResults.address,
                        latitude = apiResults.latitude,
                        longitude = apiResults.longitude
                    )
                }
        )
    }
}
