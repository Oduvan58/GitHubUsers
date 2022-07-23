package by.yancheuski.githubusers

import android.app.Application
import android.content.Context
import by.yancheuski.githubusers.di.AppComponent
import by.yancheuski.githubusers.di.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy { DaggerAppComponent.create() }
}

val Context.app: App get() = applicationContext as App