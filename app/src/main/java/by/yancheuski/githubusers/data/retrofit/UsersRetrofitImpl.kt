package by.yancheuski.githubusers.data.retrofit

import by.yancheuski.githubusers.domain.entities.UserEntity
import by.yancheuski.githubusers.domain.entities.UserProfileEntity
import by.yancheuski.githubusers.domain.repos.UsersRepo
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UsersRetrofitImpl : UsersRepo {

    private val baseUrl = "https://api.github.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private var usersApi: GitHubUsersApi = retrofit.create(GitHubUsersApi::class.java)

    override fun getUsers(): Single<List<UserEntity>> = usersApi.getListOfUsers()

    override fun getUser(login: String): Single<UserProfileEntity> = usersApi.getUserGit(login)
}