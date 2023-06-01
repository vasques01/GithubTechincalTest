package com.example.githubtechincaltest.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubtechincaltest.data.model.User
import com.example.githubtechincaltest.data.domain.GithubRepositoryImpl
import com.example.githubtechincaltest.util.UserTrie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val githubRepository: GithubRepositoryImpl): ViewModel() {
    private val _state: MutableState<MainViewState> = mutableStateOf(MainViewState())
    val state: State<MainViewState> = _state

    private var originalUsers: List<User> = emptyList()

    private val userTrie = UserTrie()

    private var currentPage = 1
    private val pageSize = 30

    init {
        // Fetch initial data
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val users = githubRepository.getUsers(currentPage, pageSize)
                originalUsers = users
                initTrie(users = users)
                val newState = MainViewState(users = users)
                _state.value = newState
            } catch (e: Exception) {
                val newState = MainViewState(error = true)
                _state.value = newState
            }
        }
    }

    fun loadMoreData() {
        viewModelScope.launch {
            currentPage++

            try {
                val currentUsers = _state.value.users
                val newUsers = githubRepository.getUsers(currentPage, pageSize)
                initTrie(newUsers)
                val newState = MainViewState(users = currentUsers + newUsers)
                _state.value = newState
            } catch (e: Exception) {
                val newState = MainViewState(error = true)
                _state.value = newState
            }
        }
    }

    private fun initTrie(users: List<User>) {
        users.forEach { user ->
            userTrie.insertUser(user.login)
        }
    }

    fun searchUsers(query: String) {
        viewModelScope.launch {
            try {
                var isSearching = false
                val users = if (query.isNotEmpty()) {
                    isSearching = true
                    val usernameList = userTrie.searchUsers(query)
                    _state.value.users.filter { it.login in usernameList }
                } else {
                    isSearching = false
                    // Show the original list of users
                    originalUsers
                }
                val newState = MainViewState(
                    users = users,
                    isSearching = isSearching,
                    error = false
                )
                _state.value = newState
            } catch (e: Exception) {
                val newState = MainViewState(
                    error = true
                )
                _state.value = newState
            }
        }
    }
}