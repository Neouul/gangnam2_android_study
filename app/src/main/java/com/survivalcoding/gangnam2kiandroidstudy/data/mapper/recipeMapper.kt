package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDTO
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.toCategory

fun RecipeDTO.toModel(): Recipe {
    return Recipe(
        id = id ?: 0,
        category = category?.toCategory() ?: RecipeCategory.NONE,
        name = name ?: EMPTY_STRING,
        imageUrl = imageUrl ?: EMPTY_STRING,
        chef = chef ?: EMPTY_STRING,
        time = time ?: EMPTY_STRING,
        rating = rating ?: EMPTY_DOUBLE,
        ingredients = ingredients
            ?.mapNotNull { it?.toModel() }
            ?: emptyList()
    )
}