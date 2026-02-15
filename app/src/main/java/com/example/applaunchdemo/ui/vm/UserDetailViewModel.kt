package com.example.applaunchdemo.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applaunchdemo.db.UserEntity
import com.example.applaunchdemo.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    fun userFlow(userId: Int): StateFlow<UserEntity?> =
        repository.getUserById(userId)
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                null
            )

    fun updateUser(user: UserEntity) {
        viewModelScope.launch {
            repository.updateUser(user)
        }
    }
}