package com.raytrack.data.repository.destination

import com.raytrack.data.localdb.entity.destination.DestinationEntity
import kotlinx.coroutines.flow.Flow

internal interface DestinationRepository {

    fun getFavorites(): Flow<List<DestinationEntity>>

    fun getRecents(): Flow<List<DestinationEntity>>

    fun searchFavorites(
        query: String
    ): Flow<List<DestinationEntity>>

    fun searchRecents(
        query: String
    ): Flow<List<DestinationEntity>>

    suspend fun insert(
        destination: DestinationEntity
    )

    suspend fun update(
        destination: DestinationEntity
    )

    suspend fun deleteById(
        id: Long
    )

    suspend fun getById(
        id: Long
    ): DestinationEntity?
}
