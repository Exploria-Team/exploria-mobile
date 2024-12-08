package com.app.exploria.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.exploria.data.database.dao.CategoryDao
import com.app.exploria.data.database.entities.CategoryEntity

@Database(entities = [CategoryEntity::class], version = 1, exportSchema = true)
abstract class CategoryDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}
