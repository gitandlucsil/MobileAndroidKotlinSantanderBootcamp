package br.com.andlucsil.minhasreceitasapp.data.repository

import br.com.andlucsil.minhasreceitasapp.data.dao.RecipeDao
import br.com.andlucsil.minhasreceitasapp.data.mapper.toDomain
import br.com.andlucsil.minhasreceitasapp.data.mapper.toEntity
import br.com.andlucsil.minhasreceitasapp.domain.model.RecipeDomain
import br.com.andlucsil.minhasreceitasapp.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepositoryImpl(private val dao : RecipeDao) : RecipeRepository {

    override suspend fun getAll(): List<RecipeDomain> = withContext(Dispatchers.IO) {
        dao.getAll().map {
            it.toDomain()
        }
    }

    override suspend fun insert(recipe: RecipeDomain) = withContext(Dispatchers.IO) {
        dao.insert(recipe.toEntity())
    }
}