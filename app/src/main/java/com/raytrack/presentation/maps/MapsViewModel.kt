package com.raytrack.presentation.maps

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raytrack.data.repository.destination.usecase.DestinationCommandUseCase
import com.raytrack.data.repository.maps.GeocodingSearchLocationUseCase
import com.raytrack.data.repository.maps.ObserveLocationUseCase
import com.raytrack.presentation.maps.events.MapsUiEffects
import com.raytrack.presentation.maps.events.MapsUiEffects.ClearSearchResults
import com.raytrack.presentation.maps.events.MapsUiEffects.ShowSearchResults
import com.raytrack.presentation.maps.events.MapsUiState
import com.raytrack.presentation.maps.model.MapSearchResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
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
    private val _uiEffect = MutableSharedFlow<MapsUiEffects>()

    private val _searchResults =
        MutableStateFlow<List<MapSearchResult>>(emptyList())

    internal val searchResults: StateFlow<List<MapSearchResult>> =
        _searchResults.asStateFlow()

    internal val inputQuery = mutableStateOf("")
    internal val showSearchResults = mutableStateOf(true)
    internal val currentLocation = mutableStateOf<Position?>(null)

    var selectedPlace: MapSearchResult? = null

    private var isLocationUpdatesStarted = false

    internal fun searchListGlassResults(
        query: String
    ) = flow<MapsUiEffects> {
        if (query.length < 4) {
            emit(ClearSearchResults)
            return@flow
        }

        geocodingSearchLocationUseCase.invoke(query)
            .collect { result ->
                if (result.isEmpty()) {
                    _uiEffect.emit(
                        ShowSearchResults(
                            results = listOf(
                                MapSearchResult(
                                    title = "No hay datos disponibles",
                                    address = "",
                                    latitude = 0.0,
                                    longitude = 0.0
                                )
                            )

                        )
                    )
                } else{
                    _uiEffect.emit(
                        ShowSearchResults(result)
                    )
                }
            }
    }.catch {

    }

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

    internal fun updateCurrentLocation(location: Position) {
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

    internal fun onMapError() {
        _uiState.value = MapsUiState.ErrorUiState
    }

    internal fun updateSearchResults(results: List<MapSearchResult>) {
        _searchResults.value = results
    }

    internal fun uiState(): StateFlow<MapsUiState> = _uiState.asStateFlow()
    internal fun uiEffect(): SharedFlow<MapsUiEffects> = _uiEffect.asSharedFlow()
}
