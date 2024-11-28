package com.app.exploria.data.database

import androidx.room.TypeConverter
import com.app.exploria.data.models.destinationItem.DestinationItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DestinationItemConverter {
    @TypeConverter
    fun fromDestinationItemList(value: List<DestinationItem>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toDestinationItemList(value: String): List<DestinationItem> {
        val type = object : TypeToken<List<DestinationItem>>() {}.type
        return Gson().fromJson(value, type)
    }
}
