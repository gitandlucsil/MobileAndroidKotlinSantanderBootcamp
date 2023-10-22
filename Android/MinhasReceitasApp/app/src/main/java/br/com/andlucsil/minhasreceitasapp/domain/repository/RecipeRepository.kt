package br.com.andlucsil.minhasreceitasapp.domain.repository

import br.com.andlucsil.minhasreceitasapp.domain.model.RecipeDomain

interface RecipeRepository {

    suspend fun getAll() : List<RecipeDomain>
    suspend fun insert(recipe : RecipeDomain)
}