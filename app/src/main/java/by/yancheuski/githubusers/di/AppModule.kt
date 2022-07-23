package by.yancheuski.githubusers.di

import by.yancheuski.githubusers.data.retrofit.GitHubUsersApi
import by.yancheuski.githubusers.data.retrofit.UsersRetrofitImpl
import by.yancheuski.githubusers.domain.repos.UsersRepo
import by.yancheuski.githubusers.view.details.UserProfileViewModel
import by.yancheuski.githubusers.view.list.UsersViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    private val baseUrl = "https://api.github.com/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideGitHubUsersApi(retrofit: Retrofit): GitHubUsersApi {
        return retrofit.create(GitHubUsersApi::class.java)
    }

    @Singleton
    @Provides
    fun provideUsersRepo(api: GitHubUsersApi): UsersRepo {
        return UsersRetrofitImpl(api)
    }

    @Provides
    @Singleton
    fun provideUsersViewModel(usersRepo: UsersRepo): UsersViewModel {
        return UsersViewModel(usersRepo)
    }

    @Provides
    @Singleton
    fun provideUserDetailsViewModel(usersRepo: UsersRepo): UserProfileViewModel {
        return UserProfileViewModel(usersRepo)
    }
}