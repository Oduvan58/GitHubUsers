package by.yancheuski.githubusers

import android.app.Application
import android.content.Context

class App : Application() {

//    val usersRepo: UsersRepository by lazy { UsersRepositoryImpl() }
}

val Context.app: App get() = applicationContext as App