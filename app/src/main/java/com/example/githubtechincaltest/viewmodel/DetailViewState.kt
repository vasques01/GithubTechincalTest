package com.example.githubtechincaltest.viewmodel

import com.example.githubtechincaltest.data.model.Repository
import com.example.githubtechincaltest.data.model.User

data class DetailViewState(
    val user: User? = null,
    val repos: List<Repository>? = emptyList(),
    val error: Boolean = false,
    val isLoading: Boolean = false
)