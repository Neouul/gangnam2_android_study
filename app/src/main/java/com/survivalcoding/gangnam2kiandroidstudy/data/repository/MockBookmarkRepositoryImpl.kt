package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.User
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import javax.inject.Inject

class MockBookmarkRepositoryImpl @Inject constructor(

) : BookmarkRepository {
    private var _user = User(
        id = 1,
        name = "admin",
        savedRecipeIds = emptyList()
    )

    override suspend fun getBookmarkedRecipeIds(): Set<Long> {
        return _user.savedRecipeIds.toSet()
    }

    override suspend fun addBookmark(recipeId: Long) {
        val current = _user.savedRecipeIds
        _user = _user.copy(savedRecipeIds = current + recipeId)
    }

    override suspend fun removeBookmark(recipeId: Long) {
        val current = _user.savedRecipeIds
        _user = _user.copy(savedRecipeIds = current - recipeId)
    }

}