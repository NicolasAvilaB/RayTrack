package com.raytrack.data.localdb.dao.destination

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.raytrack.data.localdb.entity.destination.DestinationEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface DestinationDao {
    @Query("""
        SELECT *
        FROM destinations
        WHERE isFavorite = 1
        ORDER BY title
    """)
    fun getFavorites(): Flow<List<DestinationEntity>>

    @Query("""
        SELECT *
        FROM destinations
        WHERE isFavorite = 0
        ORDER BY title
    """)
    fun getRecents(): Flow<List<DestinationEntity>>

    @Query("""
        SELECT *
        FROM destinations
        WHERE isFavorite = 1
        AND (
            title LIKE '%' || :query || '%'
            OR address LIKE '%' || :query || '%'
        )
        ORDER BY title
    """)
    fun searchFavorites(
        query: String
    ): Flow<List<DestinationEntity>>

    @Query("""
        SELECT *
        FROM destinations
        WHERE isFavorite = 0
        AND (
            title LIKE '%' || :query || '%'
            OR address LIKE '%' || :query || '%'
        )
        ORDER BY lastVisited DESC
    """)
    fun searchRecents(
        query: String
    ): Flow<List<DestinationEntity>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insert(
        destination: DestinationEntity
    )

    @Update
    suspend fun update(destination: DestinationEntity)

    @Query("""
        DELETE FROM destinations
        WHERE id = :id
    """)
    suspend fun deleteById(
        id: Long
    )

    @Query("""
        SELECT *
        FROM destinations
        WHERE id = :id
        LIMIT 1
    """)
    suspend fun getById(
        id: Long
    ): DestinationEntity?

    @Query("""
        UPDATE destinations
        SET lastVisited = :lastVisited
        WHERE id = :id
    """)
    suspend fun updateLastVisited(
        id: Long,
        lastVisited: Long
    )
}
