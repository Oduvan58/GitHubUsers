package by.yancheuski.githubusers.data

import by.yancheuski.githubusers.domain.entities.UserEntity
import by.yancheuski.githubusers.domain.entities.UserProfileEntity
import by.yancheuski.githubusers.domain.repos.UsersRepo
import io.reactivex.rxjava3.core.Single

class UsersRepoImpl : UsersRepo {

    private val data: List<UserEntity> = listOf(
        UserEntity("mojombo", 1, "https://avatars.githubusercontent.com/u/1?v=4"),
        UserEntity("defunkt", 2, "https://avatars.githubusercontent.com/u/2?v=4"),
        UserEntity("pjhyett", 3, "https://avatars.githubusercontent.com/u/3?v=4"),
    )

    override fun getUsers(): Single<List<UserEntity>> = Single.just(data)

    override fun getUser(login: String): Single<UserProfileEntity> {
        TODO("Not yet implemented")
    }
}