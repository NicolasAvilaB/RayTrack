package com.raytrack.presentation.maps.events

import com.raytrack.presentation.maps.model.MapSearchResult

internal sealed class MapsUiEffects {
    data class ShowSearchResults(
        val results: List<MapSearchResult>
    ) : MapsUiEffects()

    data object ClearSearchResults : MapsUiEffects()
}
