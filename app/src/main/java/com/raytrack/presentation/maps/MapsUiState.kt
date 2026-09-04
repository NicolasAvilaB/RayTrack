package com.raytrack.presentation.maps

internal sealed class MapsUiState {
    data object LoadingUiState : MapsUiState()
    data object DisplayUiState : MapsUiState()
}
