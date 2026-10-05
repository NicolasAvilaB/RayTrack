package com.raytrack.presentation.maps

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raytrack.data.repository.destination.usecase.DestinationCommandUseCase
import com.raytrack.data.repository.maps.ObserveLocationUseCase
import com.raytrack.ui.screens.mapscreen.model.MapSearchResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.maplibre.spatialk.geojson.Position

internal class MapsViewModel(
    private val commandDestUseCase: DestinationCommandUseCase,
    private val observeLocationUseCase: ObserveLocationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MapsUiState>(
        MapsUiState.LoadingUiState
    )

    val uiState: StateFlow<MapsUiState> = _uiState.asStateFlow()

    internal val inputQuery = mutableStateOf("")
    internal val showSearchResults = mutableStateOf(true)
    internal val currentLocation = mutableStateOf<Position?>(null)

    var selectedPlace: MapSearchResult? = null

    internal fun onQueryChange(query: String) {
        inputQuery.value = query
    }

    internal fun updateCurrentLocation(location: Position) {
        currentLocation.value = location
    }

    private var isLocationUpdatesStarted = false

    internal fun startLocationUpdates() {
        if (isLocationUpdatesStarted) return

        isLocationUpdatesStarted = true

        viewModelScope.launch {
            observeLocationUseCase.invoke()
                .collect { location ->
                    updateCurrentLocation(
                        Position(
                            latitude = location.latitude,
                            longitude = location.longitude
                        )
                    )
                }
        }
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

    internal fun onMapError() {
        _uiState.value = MapsUiState.ErrorUiState
    }
}