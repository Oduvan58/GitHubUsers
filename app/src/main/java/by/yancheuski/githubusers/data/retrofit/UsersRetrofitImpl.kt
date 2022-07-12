package by.yancheuski.githubusers.data.retrofit

import by.yancheuski.githubusers.domain.entities.UserEntity
import by.yancheuski.githubusers.domain.entities.UserProfileEntity
import by.yancheuski.githubusers.domain.repos.UsersRepo
import io.reactivex.rxjava3.core.Single

class UsersRetrofitImpl(
    private val usersApi: GitHubUsersApi,
) : UsersRepo {

    override fun getUsers(): Single<List<UserEntity>> = usersApi.getListOfUsers()

    override fun getUser(login: String): Single<UserProfileEntity> = usersApi.getUserGit(login)
}