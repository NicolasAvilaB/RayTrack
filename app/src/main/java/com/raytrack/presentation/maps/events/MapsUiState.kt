package com.raytrack.presentation.maps.events

internal sealed class MapsUiState {
    data object LoadingUiState : MapsUiState()
    data object DisplayUiState : MapsUiState()
    data object ErrorUiState : MapsUiState()
}
