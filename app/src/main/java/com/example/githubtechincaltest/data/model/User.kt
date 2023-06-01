package com.example.githubtechincaltest.data.model

import com.google.gson.annotations.SerializedName

data class User(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val url: String,
    val name: String,
    val company: String,
    val bio: String,
    val followers: Int,
    @SerializedName("public_repos")
    val publicRepos: Int,
    val email: String
)