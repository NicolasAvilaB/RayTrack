package com.raytrack.data.repository.destination.usecase

import com.raytrack.data.localdb.entity.destination.DestinationEntity
import com.raytrack.data.repository.destination.DestinationRepository

internal class DestinationCommandUseCase(
    private val repository: DestinationRepository
) {
    suspend fun insert(
        destination: DestinationEntity
    ) {
        repository.insert(destination)
    }

    suspend fun update(
        destination: DestinationEntity
    ) {
        repository.update(destination)
    }

    suspend fun deleteById(
        id: Long
    ) {
        repository.deleteById(id)
    }
}
