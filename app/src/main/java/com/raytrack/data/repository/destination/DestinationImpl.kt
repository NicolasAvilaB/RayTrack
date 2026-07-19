package com.raytrack.data.repository.destination

import com.raytrack.data.localdb.dao.destination.DestinationDao
import com.raytrack.data.localdb.entity.destination.DestinationEntity
import kotlinx.coroutines.flow.Flow

internal class DestinationImpl(
    private val dao: DestinationDao
) : DestinationRepository {

    override fun getFavorites(): Flow<List<DestinationEntity>> =
        dao.getFavorites()

    override fun getRecents(): Flow<List<DestinationEntity>> =
        dao.getRecents()

    override fun searchFavorites(query: String): Flow<List<DestinationEntity>> =
        dao.searchFavorites(query)

    override fun searchRecents(query: String): Flow<List<DestinationEntity>> =
        dao.searchRecents(query)

    override suspend fun insert(destination: DestinationEntity) =
        dao.insert(destination)

    override suspend fun update(destination: DestinationEntity) =
        dao.update(destination)

    override suspend fun deleteById(id: Long) =
        dao.deleteById(id)

    override suspend fun getById(id: Long): DestinationEntity? =
        dao.getById(id)

}
