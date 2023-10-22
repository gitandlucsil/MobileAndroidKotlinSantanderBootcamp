package br.com.andlucsil.minhasreceitasapp.presentation.recipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.andlucsil.minhasreceitasapp.databinding.ItemRecipeBinding
import br.com.andlucsil.minhasreceitasapp.domain.model.RecipeDomain

class RecipeAdapter : ListAdapter<RecipeDomain, RecipeAdapter.ViewHolder>(DiffCallback()) {

    val click : (RecipeDomain) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipeBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemRecipeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : RecipeDomain) {
            binding.tvTitle.text = item.name
        }
    }
}

class DiffCallback: DiffUtil.ItemCallback<RecipeDomain>() {

    override fun areItemsTheSame(oldItem: RecipeDomain, newItem: RecipeDomain) = oldItem == newItem

    override fun areContentsTheSame(oldItem: RecipeDomain, newItem: RecipeDomain) =
        oldItem.id == newItem.id

}