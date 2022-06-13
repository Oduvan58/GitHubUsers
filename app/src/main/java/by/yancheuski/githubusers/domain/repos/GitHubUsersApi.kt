package by.yancheuski.githubusers.domain.repos

import by.yancheuski.githubusers.domain.entities.UserEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubUsersApi {

    @GET("users/")
    fun getListOfUsers(@Query("login") login: String): Call<UserEntity>
}