package com.app.exploria.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.exploria.data.database.dao.DestinationDao
import com.app.exploria.data.database.entities.DestinationEntity

@Database(entities = [DestinationEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun destinationDao(): DestinationDao
}
