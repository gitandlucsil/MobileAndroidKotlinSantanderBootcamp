package br.com.andlucsil.minhasreceitasapp.domain.usecase

import br.com.andlucsil.minhasreceitasapp.domain.model.RecipeDomain
import br.com.andlucsil.minhasreceitasapp.domain.repository.RecipeRepository

class InsertRecipeUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipe: RecipeDomain) = repository.insert(recipe)
}