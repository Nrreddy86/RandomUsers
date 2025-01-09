package com.nisum.randomusersapp.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val apiServices: ApiServices){

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
