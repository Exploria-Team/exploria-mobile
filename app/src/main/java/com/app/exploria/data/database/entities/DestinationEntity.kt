package com.app.exploria.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "destinations")
data class DestinationEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val city: String,
    val description: String,
    val photoUrl: Int,
    val entryFee: Int,
    val visitDurationMinutes: Int,
    val averageRating: Float,
    val lat: Double,
    val lon: Double
)
