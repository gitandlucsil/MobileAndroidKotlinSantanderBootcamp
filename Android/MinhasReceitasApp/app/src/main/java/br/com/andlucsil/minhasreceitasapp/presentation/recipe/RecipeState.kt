package br.com.andlucsil.minhasreceitasapp.presentation.recipe

import br.com.andlucsil.minhasreceitasapp.domain.model.RecipeDomain

interface RecipeState {
    object Loading : RecipeState
    object Empty : RecipeState
    data class Success (val recipe : List<RecipeDomain>) : RecipeState
    data class Error(val message : String) : RecipeState
}