package com.survivalcoding.gangnam2kiandroidstudy.domain.model

enum class RecipeCategory {
    ALL,
    INDIAN,
    ITALIAN,
    ASIAN,
    CHINESE,
    FRUIT,
    VEGETABLES,
    PROTEIN,
    CEREAL,
    LOCAL_DISHES,
    DINNER,
    BREAKFAST,
    SPANISH,
    LUNCH,
    NONE,
}

fun String.toCategory(): RecipeCategory {
    return when (this) {
        "All" -> RecipeCategory.ALL
        "Indian" -> RecipeCategory.INDIAN
        "Italian" -> RecipeCategory.ITALIAN
        "Asian" -> RecipeCategory.ASIAN
        "Chinese" -> RecipeCategory.CHINESE
        "Fruit" -> RecipeCategory.FRUIT
        "Vegetables" -> RecipeCategory.VEGETABLES
        "Protein" -> RecipeCategory.PROTEIN
        "Cereal" -> RecipeCategory.CEREAL
        "Local Dishes", "Local Dish" -> RecipeCategory.LOCAL_DISHES
        "Dinner" -> RecipeCategory.DINNER
        "BreakFast" -> RecipeCategory.BREAKFAST
        "Spanish" -> RecipeCategory.SPANISH
        "Lunch" -> RecipeCategory.LUNCH
        else -> RecipeCategory.NONE
    }
}

fun RecipeCategory.toFormatString(): String {
    return when (this){
        RecipeCategory.ALL -> "All"
        RecipeCategory.INDIAN -> "Indian"
        RecipeCategory.ITALIAN -> "Italian"
        RecipeCategory.ASIAN -> "Asian"
        RecipeCategory.CHINESE -> "Chinese"
        RecipeCategory.FRUIT -> "Fruit"
        RecipeCategory.VEGETABLES -> "Vegetables"
        RecipeCategory.PROTEIN -> "Protein"
        RecipeCategory.CEREAL -> "Cereal"
        RecipeCategory.LOCAL_DISHES -> "Local Dish"
        RecipeCategory.DINNER -> "Dinner"
        RecipeCategory.BREAKFAST -> "BreakFast"
        RecipeCategory.SPANISH -> "Spanish"
        RecipeCategory.LUNCH -> "Lunch"
        RecipeCategory.NONE -> ""
    }
}