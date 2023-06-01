package com.example.githubtechincaltest.data

import com.example.githubtechincaltest.data.model.Repository
import com.example.githubtechincaltest.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("users")
    suspend fun getUsers(@Query("since") since: Int, @Query("per_page") perPage: Int): List<User>

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): User

    @GET("users/{username}/repos")
    suspend fun getUserRepositories(@Path("username") username: String): List<Repository>
}