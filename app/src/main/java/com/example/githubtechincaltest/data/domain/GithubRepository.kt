package com.example.githubtechincaltest.data.domain

import com.example.githubtechincaltest.data.model.Repository
import com.example.githubtechincaltest.data.model.User

interface GithubRepository {
    suspend fun getUsers(since: Int, perPage: Int): List<User>
    suspend fun getUser(username: String): User
    suspend fun getUserRepositories(username: String): List<Repository>
}