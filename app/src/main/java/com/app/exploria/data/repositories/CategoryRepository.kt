package com.app.exploria.data.repositories

import com.app.exploria.data.database.dao.CategoryDao
import com.app.exploria.data.database.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryDao: CategoryDao) {

    suspend fun saveStaticCategories() {
        val staticCategories = listOf(
            CategoryEntity(id = 1, name = "Bahari"),
            CategoryEntity(id = 2, name = "Budaya"),
            CategoryEntity(id = 3, name = "Cagar Alam"),
            CategoryEntity(id = 4, name = "Kebun Binatang"),
            CategoryEntity(id = 5, name = "Pusat Perbelanjaan"),
            CategoryEntity(id = 6, name = "Situs Sejarah"),
            CategoryEntity(id = 7, name = "Taman Hiburan"),
            CategoryEntity(id = 8, name = "Teater & Pertunjukan"),
            CategoryEntity(id = 9, name = "Tempat Ibadah")
        )
        categoryDao.deleteAllCategories()
        for (category in staticCategories) {
            categoryDao.insertCategory(category)
        }
    }

    fun getCategories(): Flow<List<CategoryEntity>> {
        return categoryDao.getAllCategories()
    }
}

