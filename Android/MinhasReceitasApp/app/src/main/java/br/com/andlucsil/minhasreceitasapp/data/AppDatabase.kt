package br.com.andlucsil.minhasreceitasapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.andlucsil.minhasreceitasapp.data.dao.RecipeDao
import br.com.andlucsil.minhasreceitasapp.data.entity.RecipeEntity

@Database(
    entities = [
        RecipeEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao() : RecipeDao
}