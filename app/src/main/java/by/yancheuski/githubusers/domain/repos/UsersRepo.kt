package by.yancheuski.githubusers.domain.repos

import by.yancheuski.githubusers.domain.entities.UserEntity

interface UsersRepo {

    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null,
    )

    fun getUser(
        login: String,
        onSuccess: (user: UserEntity) -> Unit,
        onError: ((Throwable) -> Unit)? = null,
    )
}