package by.yancheuski.githubusers.domain.repos

import by.yancheuski.githubusers.domain.entities.UserEntity
import by.yancheuski.githubusers.domain.entities.UserProfileEntity
import io.reactivex.rxjava3.core.Single

interface UsersRepo {

    fun getUsers(): Single<List<UserEntity>>

    fun getUser(login: String): Single<UserProfileEntity>
}