package com.raytrack.data.repository.destination.usecase

import com.raytrack.data.repository.destination.DestinationRepository

internal class GetDestinationUseCase(
    private val repository: DestinationRepository
) {
    fun getFavorites() =
        repository.getFavorites()

    fun getRecents() =
        repository.getRecents()

    suspend fun getById(
        id: Long
    ) = repository.getById(id)
}
