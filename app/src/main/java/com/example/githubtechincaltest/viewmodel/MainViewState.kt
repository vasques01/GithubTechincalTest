package com.example.githubtechincaltest.viewmodel

import com.example.githubtechincaltest.data.model.Repository
import com.example.githubtechincaltest.data.model.User

data class MainViewState(
    val user: User? = null,
    val users: List<User> = emptyList(),
    val repositories: List<Repository> = emptyList(),
    val isSearching: Boolean = false,
    val isLoading: Boolean = false,
    val error: Boolean = false
)
