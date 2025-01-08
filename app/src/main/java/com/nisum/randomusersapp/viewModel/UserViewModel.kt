package com.nisum.randomusersapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nisum.randomusersapp.model.ApiResult
import com.nisum.randomusersapp.model.User
import com.nisum.randomusersapp.model.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository = UserRepository()) : ViewModel() {

    //    private val repository = UserRepository()
    private val _usersState = MutableStateFlow<ApiResult<List<User>>>(ApiResult.Loading)
    val usersState: StateFlow<ApiResult<List<User>>> = _usersState

    fun fetchUsers(count: Int) {
        viewModelScope.launch {
            _usersState.value = ApiResult.Loading

            try {
                val result = repository.fetchUsers(count)
                _usersState.value = result
            } catch (e: Exception) {
                _usersState.value = ApiResult.Error(e.message ?: "Unknown error")
            }

        }
    }

}
