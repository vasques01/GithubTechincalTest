package com.example.githubtechincaltest.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubtechincaltest.data.model.User
import com.example.githubtechincaltest.data.domain.GithubRepository
import javax.inject.Inject

class GithubUsersPagingSource @Inject constructor(private val githubRepository: GithubRepository): PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: 1

        return try {
            val users = githubRepository.getUsers(page, params.loadSize)
            LoadResult.Page(
                data = users,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (users.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        val anchorPosition = state.anchorPosition ?: return null

        val anchorItem = state.closestItemToPosition(anchorPosition) ?: return null

        return anchorItem.id
    }
}