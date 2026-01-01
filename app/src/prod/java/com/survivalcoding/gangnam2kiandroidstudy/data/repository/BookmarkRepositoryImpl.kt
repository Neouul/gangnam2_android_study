package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.local.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.local.BookmarkEntity
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository

class BookmarkRepositoryImpl(
    private val bookmarkDao: BookmarkDao
) : BookmarkRepository {

    override suspend fun getBookmarkedRecipeIds(): Set<Long> {
        return bookmarkDao.getBookmarkedRecipeIds().toSet()
    }

    override suspend fun addBookmark(recipeId: Long) {
        bookmarkDao.insertBookmark(BookmarkEntity(recipeId = recipeId))
    }

    override suspend fun removeBookmark(recipeId: Long) {
        bookmarkDao.deleteBookmark(recipeId)
    }
}
