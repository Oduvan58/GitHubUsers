package by.yancheuski.githubusers

import android.app.Application
import android.content.Context
import by.yancheuski.githubusers.data.retrofit.UsersRetrofitImpl
import by.yancheuski.githubusers.domain.repos.UsersRepo

class App : Application() {

    val usersRepo: UsersRepo by lazy { UsersRetrofitImpl() }
}

val Context.app: App get() = applicationContext as App