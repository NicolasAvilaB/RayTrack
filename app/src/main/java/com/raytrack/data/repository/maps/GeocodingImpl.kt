package com.raytrack.data.repository.maps

import com.raytrack.data.models.MapSearchApiResult
import com.raytrack.data.models.RemoteMapSearch
import com.raytrack.data.remote.NominatimApiServices

internal class GeocodingImpl(
    private val api: NominatimApiServices
) : GeocodingRepository {

    override suspend fun search(
        query: String
    ): List<MapSearchApiResult> {
        if (query.isBlank()) {
            return emptyList()
        }

        return api.search(query).map { remote ->
            MapSearchApiResult(
                title = remote?.displayName
                    ?.substringBefore(","),
                address = remote?.displayName
                    ?.substringAfter(",")
                    ?.trim(),
                latitude = remote?.latitude,
                longitude = remote?.longitude
            )
        }
    }
}
