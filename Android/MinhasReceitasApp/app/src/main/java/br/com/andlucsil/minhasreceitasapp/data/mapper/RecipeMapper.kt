package br.com.andlucsil.minhasreceitasapp.data.mapper

import br.com.andlucsil.minhasreceitasapp.data.entity.RecipeEntity
import br.com.andlucsil.minhasreceitasapp.domain.model.RecipeDomain

fun RecipeDomain.toEntity() = RecipeEntity(
    id = id,
    name = name
)

fun RecipeEntity.toDomain() = RecipeDomain(
    id = id,
    name = name
)