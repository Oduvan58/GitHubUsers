package by.yancheuski.githubusers.data.retrofit

import by.yancheuski.githubusers.domain.entities.UserEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubUsersApi {

    @GET("users")
    fun getListOfUsers(): Single<List<UserEntity>>

    @GET("users/{login}")
    fun getUserGit(@Path("login") login: String): Single<UserEntity>
}