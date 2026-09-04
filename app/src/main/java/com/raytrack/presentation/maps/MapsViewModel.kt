package com.raytrack.presentation.maps

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.raytrack.data.repository.destination.usecase.DestinationCommandUseCase
import com.raytrack.ui.screens.mapscreen.model.MapSearchResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class MapsViewModel(
    private val commandDestUseCase: DestinationCommandUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MapsUiState>(
        MapsUiState.LoadingUiState
    )

    val uiState: StateFlow<MapsUiState> = _uiState.asStateFlow()

    internal val inputQuery = mutableStateOf("")
    internal val showSearchResults = mutableStateOf(true)
    internal val currentLocation = mutableStateOf<LatLng?>(null)
    var selectedPlace: MapSearchResult? = null

    internal fun onQueryChange(query: String) {
        inputQuery.value = query
    }

    internal fun updateCurrentLocation(location: LatLng) {
        currentLocation.value = location
    }

    internal fun selectPlace(place: MapSearchResult) {
        selectedPlace = place
    }

    internal fun toggleSearchResults() {
        showSearchResults.value = !showSearchResults.value
    }

    internal fun clearQuery() {
        inputQuery.value = ""
    }

    internal fun clearSelectedPlace() {
        selectedPlace = null
    }

    internal fun onMapLoaded() {
        _uiState.value = MapsUiState.DisplayUiState
    }
}
