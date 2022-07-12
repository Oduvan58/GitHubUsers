package by.yancheuski.githubusers

import android.app.Application
import android.content.Context
import by.yancheuski.githubusers.data.retrofit.GitHubUsersApi
import by.yancheuski.githubusers.data.retrofit.UsersRetrofitImpl
import by.yancheuski.githubusers.domain.repos.UsersRepo
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    val usersRepo: UsersRepo by lazy { UsersRetrofitImpl(usersApi) }

    private val baseUrl = "https://api.github.com/"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    private val usersApi: GitHubUsersApi by lazy { retrofit.create(GitHubUsersApi::class.java) }
}

val Context.app: App get() = applicationContext as App