package br.com.andlucsil.minhasreceitasapp.presentation.recipe

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import br.com.andlucsil.minhasreceitasapp.data.db
import br.com.andlucsil.minhasreceitasapp.data.repository.RecipeRepositoryImpl
import br.com.andlucsil.minhasreceitasapp.domain.model.RecipeDomain
import br.com.andlucsil.minhasreceitasapp.domain.usecase.GetAllRecipesUseCase
import br.com.andlucsil.minhasreceitasapp.domain.usecase.InsertRecipeUseCase
import kotlinx.coroutines.launch

class RecipesViewModel(
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val insertRecipesUseCase: InsertRecipeUseCase
    ) : ViewModel() {

        val state : LiveData<RecipeState> = liveData {
            emit(RecipeState.Loading)
            val state = try {
                val recipes = getAllRecipesUseCase()
                if (recipes.isEmpty()) {
                    RecipeState.Empty
                } else {
                    RecipeState.Success(recipes)
                }
            } catch (exception : Exception) {
                Log.e("Error", exception.message.toString())
                RecipeState.Error(exception.message.toString())
            }
            emit(state)
        }

    fun insert(name: String) = viewModelScope.launch {
        insertRecipesUseCase(RecipeDomain(name = name))
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            val application = checkNotNull(extras[APPLICATION_KEY])
            val repository = RecipeRepositoryImpl(application.db.recipeDao())
            return RecipesViewModel(
                getAllRecipesUseCase = GetAllRecipesUseCase(repository),
                insertRecipesUseCase = InsertRecipeUseCase(repository)
            ) as T
        }
    }
}