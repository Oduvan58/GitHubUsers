package by.yancheuski.githubusers.data

import android.os.Handler
import android.os.Looper
import by.yancheuski.githubusers.domain.entities.UserEntity
import by.yancheuski.githubusers.domain.repos.UsersRepo
import io.reactivex.rxjava3.core.Single

private const val DATA_LOADING_FAKE_DELAY = 3_000L

class UsersRepoImpl : UsersRepo {

    private val data: List<UserEntity> = listOf(
        UserEntity("mojombo", 1, "https://avatars.githubusercontent.com/u/1?v=4"),
        UserEntity("defunkt", 2, "https://avatars.githubusercontent.com/u/2?v=4"),
        UserEntity("pjhyett", 3, "https://avatars.githubusercontent.com/u/3?v=4"),
    )

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        Handler(Looper.getMainLooper()).postDelayed({
            onSuccess(data)
        }, DATA_LOADING_FAKE_DELAY)
    }

    override fun getUsers(): Single<List<UserEntity>> = Single.just(data)

    override fun getUser(
        login: String,
        onSuccess: (user: UserEntity) -> Unit,
        onError: ((Throwable) -> Unit)?,
    ) {
        TODO("Not yet implemented")
    }

    override fun getUser(login: String): Single<UserEntity> {
        TODO("Not yet implemented")
    }
}