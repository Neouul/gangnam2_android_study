package com.survivalcoding.gangnam2kiandroidstudy.data.model

data class Recipe(
    val id: Long,
    val category: String,
    val name: String,
    val imageUrl: String,
    val chef: String,
    val time: String,
    val rating: Double,
)
