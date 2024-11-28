package com.app.exploria.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.exploria.data.database.dao.ItinerariesDao
import com.app.exploria.data.database.entities.ItinerariesEntity

@Database(entities = [ItinerariesEntity::class], version = 1)
@TypeConverters(DestinationItemConverter::class)
abstract class ItinerariesDatabase : RoomDatabase() {
    abstract fun itinerariesDao(): ItinerariesDao

    companion object {
        @Volatile
        private var INSTANCE : ItinerariesDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): ItinerariesDatabase {
            if (INSTANCE == null) {
                synchronized(ItinerariesDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ItinerariesDatabase::class.java, "itineraries_database"
                    ).build()
                }
            }
            return INSTANCE as ItinerariesDatabase
        }
    }
}
