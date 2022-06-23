package by.yancheuski.githubusers.data

import by.yancheuski.githubusers.domain.entities.UserEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubUsersApi {

    @GET("users")
    fun getListOfUsers(): Call<List<UserEntity>>

    @GET("users/{login}")
    fun getUserGit(@Path("login") login: String): Call<UserEntity>
}