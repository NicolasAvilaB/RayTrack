package com.raytrack.data.localdb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raytrack.data.localdb.dao.destination.DestinationDao
import com.raytrack.data.localdb.entity.destination.DestinationEntity
import com.raytrack.data.localdb.entity.destination.DestinationTypeConverter

@Database(
    entities = [DestinationEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DestinationTypeConverter::class)
internal abstract class RayTracDatabase : RoomDatabase() {
    abstract fun destinationDao(): DestinationDao
}
