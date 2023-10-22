package br.com.andlucsil.minhasreceitasapp.domain.usecase

import br.com.andlucsil.minhasreceitasapp.domain.repository.RecipeRepository

class GetAllRecipesUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke() = repository.getAll()
}