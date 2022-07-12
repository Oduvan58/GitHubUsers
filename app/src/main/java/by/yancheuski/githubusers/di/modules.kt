package by.yancheuski.githubusers.di

import by.yancheuski.githubusers.data.retrofit.GitHubUsersApi
import by.yancheuski.githubusers.data.retrofit.UsersRetrofitImpl
import by.yancheuski.githubusers.domain.repos.UsersRepo
import by.yancheuski.githubusers.view.details.UserProfileViewModel
import by.yancheuski.githubusers.view.list.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    val baseUrl = "https://api.github.com/"

    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    single<GitHubUsersApi> { get<Retrofit>().create(GitHubUsersApi::class.java) }

    single<UsersRepo> { UsersRetrofitImpl(get()) }

    viewModel { UsersViewModel(get()) }
    viewModel { UserProfileViewModel(get()) }
}