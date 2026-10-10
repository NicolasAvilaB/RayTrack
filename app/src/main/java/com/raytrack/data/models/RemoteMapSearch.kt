package com.raytrack.data.models

import com.google.gson.annotations.SerializedName

internal data class RemoteMapSearch(
    @SerializedName("display_name") val displayName: String?,
    @SerializedName("lat") val latitude: Double?,
    @SerializedName("lon") val longitude: Double?
)
