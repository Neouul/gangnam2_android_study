package com.survivalcoding.gangnam2kiandroidstudy.data.data_source

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

class MockRecipeDataSourceImpl : RecipeDataSource {
    override suspend fun getRecipe(id: Long): Recipe {
        return Recipe(
            id = 1,
            category = "Indian",
            name = "Traditional spare ribs baked",
            imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            chef = "Chef John",
            time = "20 min",
            rating = 4.0,
            ingredients = listOf(
                Ingredient(
                    id = 3,
                    name = "Pork",
                    imageUrl = "https://cdn.pixabay.com/photo/2019/12/20/14/44/meat-4708596_1280.jpg",
                ) to 500,
                Ingredient(
                    id = 9,
                    name = "Onion",
                    imageUrl = "https://cdn.pixabay.com/photo/2013/02/21/19/14/onion-bulbs-84722_1280.jpg",
                ) to 50,
            )
        )
    }

    override suspend fun getRecipes(): List<Recipe> {
        return listOf(
            Recipe(
                id = 1,
                category = "Indian",
                name = "Traditional spare ribs baked",
                imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
                chef = "Chef John",
                time = "20 min",
                rating = 4.0,
                ingredients = listOf(
                    Ingredient(
                        id = 3,
                        name = "Pork",
                        imageUrl = "https://cdn.pixabay.com/photo/2019/12/20/14/44/meat-4708596_1280.jpg",
                    ) to 500,
                    Ingredient(
                        id = 9,
                        name = "Onion",
                        imageUrl = "https://cdn.pixabay.com/photo/2013/02/21/19/14/onion-bulbs-84722_1280.jpg",
                    ) to 50,
                )
            ),
            Recipe(
                id = 2,
                category = "Asian",
                name = "Spice roasted chicken with flavored rice",
                imageUrl = "https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
                chef = "Mark Kelvin",
                time = "20 min",
                rating = 4.0,
                ingredients = listOf(
                    Ingredient(
                        id = 6,
                        name = "Chicken",
                        imageUrl = "https://cdn.pixabay.com/photo/2010/12/10/08/chicken-1140_1280.jpg",
                    ) to 300,
                    Ingredient(
                        id = 4,
                        name = "Rice",
                        imageUrl = "https://cdn.pixabay.com/photo/2016/02/29/05/46/brown-rice-1228099_1280.jpg",
                    ) to 200,
                )
            ),
        )
    }
}