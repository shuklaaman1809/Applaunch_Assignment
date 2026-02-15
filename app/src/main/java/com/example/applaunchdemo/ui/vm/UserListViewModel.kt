package com.example.applaunchdemo.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applaunchdemo.db.UserEntity
import com.example.applaunchdemo.network.UiState
import com.example.applaunchdemo.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<UiState<List<UserEntity>>>(UiState.Loading)

    val uiState = _uiState.asStateFlow()

    init {
        observeUsers()
        syncUsers()
    }

    private fun observeUsers() {
        viewModelScope.launch {
            repository.usersFlow.collect { users ->
                _uiState.value = UiState.Success(users)
            }
        }
    }

    private fun syncUsers() {
        viewModelScope.launch {
            repository.syncUsersInBackground()
        }
    }

    fun delete(user: UserEntity) {
        viewModelScope.launch {
            repository.deleteUser(user)
        }
    }
}