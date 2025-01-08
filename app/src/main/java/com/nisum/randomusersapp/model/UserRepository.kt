package com.nisum.randomusersapp.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {

    private val apiServices = RetrofitClient().apiServices
    suspend fun fetchUsers(count: Int): ApiResult<List<User>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiServices.getRandomUsers(results = count)
                ApiResult.Success(response.results)
            } catch (e: Exception) {
                ApiResult.Error(e.localizedMessage ?: "Failed to fetch users")
            }
        }
    }
}
