package com.raytrack.presentation.maps

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raytrack.data.repository.destination.usecase.DestinationCommandUseCase
import com.raytrack.data.repository.maps.GeocodingSearchLocationUseCase
import com.raytrack.data.repository.maps.ObserveLocationUseCase
import com.raytrack.presentation.maps.model.MapSearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.maplibre.spatialk.geojson.Position

internal class MapsViewModel(
    private val commandDestUseCase: DestinationCommandUseCase,
    private val geocodingSearchLocationUseCase: GeocodingSearchLocationUseCase,
    private val observeLocationUseCase: ObserveLocationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MapsUiState>(
        MapsUiState.LoadingUiState
    )

    val uiState: StateFlow<MapsUiState> = _uiState.asStateFlow()

    private val _searchResults =
        MutableStateFlow<List<MapSearchResult>>(emptyList())

    internal val searchResults: StateFlow<List<MapSearchResult>> =
        _searchResults.asStateFlow()
    internal val inputQuery = mutableStateOf("")
    internal val showSearchResults = mutableStateOf(true)
    internal val currentLocation = mutableStateOf<Position?>(null)

    var selectedPlace: MapSearchResult? = null

    internal fun onQueryChange(query: String) {

        inputQuery.value = query
        viewModelScope.launch {
            runCatching {
                geocodingSearchLocationUseCase
                    .invoke(query)
                    .map { result ->
                        MapSearchResult(
                            title = result.title,
                            address = result.address,
                            latitude = result.latitude,
                            longitude = result.longitude
                        )
                    }
            }.onSuccess { results ->
                _searchResults.update { results }
            }.onFailure {

            }
        }
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