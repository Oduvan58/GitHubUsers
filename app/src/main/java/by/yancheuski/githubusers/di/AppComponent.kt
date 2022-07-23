package by.yancheuski.githubusers.di

import by.yancheuski.githubusers.view.details.UserProfileActivity
import by.yancheuski.githubusers.view.list.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(userProfileActivity: UserProfileActivity)
}