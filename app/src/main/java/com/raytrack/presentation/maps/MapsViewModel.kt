package com.raytrack.presentation.maps

import androidx.lifecycle.ViewModel
import com.raytrack.data.repository.destination.usecase.DestinationCommandUseCase

internal class MapsViewModel(
    private val commandDestUseCase: DestinationCommandUseCase
) : ViewModel() {

}
