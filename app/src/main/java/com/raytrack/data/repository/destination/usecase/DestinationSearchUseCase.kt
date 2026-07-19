package com.raytrack.data.repository.destination.usecase

import com.raytrack.data.repository.destination.DestinationRepository

internal class DestinationSearchUseCase(
    private val repository: DestinationRepository
) {
    fun searchFavorites(
        query: String
    ) = repository.searchFavorites(query)

    fun searchRecents(
        query: String
    ) = repository.searchRecents(query)
}
