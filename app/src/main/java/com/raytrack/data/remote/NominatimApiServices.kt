package com.raytrack.data.remote

import com.raytrack.data.models.RemoteMapSearch
import retrofit2.http.GET
import retrofit2.http.Query

internal interface NominatimApiServices {

    @GET("search")
    suspend fun search(
        @Query("q") query: String,
        @Query("format") format: String = "jsonv2",
        @Query("limit") limit: Int = 3,
        @Query("countrycodes") countryCodes: String = "cl",
    ): List<RemoteMapSearch?>
}