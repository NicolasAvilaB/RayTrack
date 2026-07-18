package com.raytrack.data.localdb.entity.destination

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raytrack.data.models.DestinationType

@Entity(tableName = "destinations")
internal data class DestinationEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val address: String,
    val icon: DestinationType,
    val isFavorite: Int,
    val lastVisited: Long = System.currentTimeMillis()
)
