package com.app.exploria.data.database.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.exploria.data.models.destinationItem.DestinationItem
import kotlinx.parcelize.Parcelize

@Entity(tableName = "itineraries_database")
@Parcelize
data class ItinerariesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "start_date")
    var startdate: String,
    @ColumnInfo(name = "finish_date")
    var finishdate: String,
    @ColumnInfo(name = "destinations")
    var destinations: List<DestinationItem>
) : Parcelable
