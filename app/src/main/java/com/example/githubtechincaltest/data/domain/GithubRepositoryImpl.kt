package com.example.githubtechincaltest.data.domain

import com.example.githubtechincaltest.data.GithubApi
import com.example.githubtechincaltest.data.model.Repository
import com.example.githubtechincaltest.data.model.User
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(private val githubApi: GithubApi): GithubRepository {
    override suspend fun getUsers(since: Int, perPage: Int): List<User> {
        return githubApi.getUsers(since, perPage)
    }

    override suspend fun getUser(username: String): User {
        return githubApi.getUser(username)
    }

    override suspend fun getUserRepositories(username: String): List<Repository> {
        return githubApi.getUserRepositories(username)
    }
}