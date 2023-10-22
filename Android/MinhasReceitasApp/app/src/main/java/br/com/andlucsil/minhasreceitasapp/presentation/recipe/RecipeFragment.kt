package br.com.andlucsil.minhasreceitasapp.presentation.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import br.com.andlucsil.minhasreceitasapp.R
import br.com.andlucsil.minhasreceitasapp.databinding.FragmentRecipeBinding
import br.com.andlucsil.minhasreceitasapp.presentation.dialog.DialogEditTextFragment
import br.com.andlucsil.minhasreceitasapp.presentation.dialog.DialogEditTextFragment.Companion.EDIT_TEXT_VALUE
import br.com.andlucsil.minhasreceitasapp.presentation.dialog.DialogEditTextFragment.Companion.FRAGMENT_RESULT
import br.com.andlucsil.minhasreceitasapp.presentation.recipe.adapter.RecipeAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RecipeFragment : Fragment() {

    private lateinit var binding: FragmentRecipeBinding
    private val adapter by lazy { RecipeAdapter() }
    private val viewModel: RecipesViewModel by viewModels {
        RecipesViewModel.Factory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupAdapter()
        observeState()
    }

    private fun setupListeners() {
        binding.fabRecipe.setOnClickListener{
            showDialog()
        }
        setFragmentResultListener(FRAGMENT_RESULT) { requestKey, bundle ->
            val nameRecipe = bundle.getString(EDIT_TEXT_VALUE) ?: ""
            viewModel.insert(nameRecipe)
        }
    }

    fun setupAdapter() {
        binding.rvRecipes.adapter = adapter
    }

    private fun observeState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when(state) {
                RecipeState.Loading -> {
                    binding.pbLoading.isVisible = true
                }
                RecipeState.Empty -> {
                    binding.pbLoading.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        "Ops, não temos nenhuma receita",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is RecipeState.Error -> {
                    binding.pbLoading.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        state.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
                is RecipeState.Success -> {
                    binding.pbLoading.isVisible = false
                    adapter.submitList(state.recipe)
                }
            }
        }
    }

    private fun showDialog() {
        DialogEditTextFragment.show(
            title = getString(R.string.title_new_recipe),
            placeHolder = getString(R.string.label_name_recipe),
            fragmentManager = parentFragmentManager
        )
    }

}