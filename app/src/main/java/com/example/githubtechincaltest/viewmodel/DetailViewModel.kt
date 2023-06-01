package com.example.githubtechincaltest.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubtechincaltest.data.domain.GithubRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val githubRepository: GithubRepositoryImpl): ViewModel() {
    private val _state: MutableState<DetailViewState> = mutableStateOf(DetailViewState())
    val state: State<DetailViewState> = _state

    fun getUser(userId: String) {
        viewModelScope.launch {
            try {
                val user = githubRepository.getUser(userId)
                val repos = githubRepository.getUserRepositories(userId)
                _state.value = DetailViewState(user, repos)
            } catch (e: Exception) {
                _state.value = DetailViewState(error = true)
            }
        }
    }
}