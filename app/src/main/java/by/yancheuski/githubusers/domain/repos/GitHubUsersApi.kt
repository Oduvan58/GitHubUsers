package by.yancheuski.githubusers.domain.repos

import by.yancheuski.githubusers.domain.entities.UserEntity
import retrofit2.Call
import retrofit2.http.GET

interface GitHubUsersApi {

    @GET("users")
    fun getListOfUsers(): Call<List<UserEntity>>
}