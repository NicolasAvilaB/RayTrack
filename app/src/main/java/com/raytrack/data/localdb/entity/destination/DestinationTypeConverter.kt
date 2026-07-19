package com.raytrack.data.localdb.entity.destination

import androidx.room.TypeConverter
import com.raytrack.data.models.DestinationType

internal class DestinationTypeConverter {

    @TypeConverter
    fun fromType(type: DestinationType): String =
        type.name

    @TypeConverter
    fun toType(value: String): DestinationType =
        DestinationType.valueOf(value)
}
