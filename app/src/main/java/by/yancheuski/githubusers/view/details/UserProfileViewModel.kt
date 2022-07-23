package by.yancheuski.githubusers.view.details

import androidx.lifecycle.ViewModel
import by.yancheuski.githubusers.domain.entities.UserProfileEntity
import by.yancheuski.githubusers.domain.repos.UsersRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

class UserProfileViewModel(private val userRepo: UsersRepo) : ViewModel() {

    val userLiveData: Observable<UserProfileEntity> = BehaviorSubject.create()
    val errorLiveData: Observable<Throwable> = BehaviorSubject.create()
    private lateinit var login: String

    fun onRefresh(login: String) {
        this.login = login
        loadUser()
    }

    private fun loadUser() {
        userRepo.getUser(login = login)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    userLiveData.mutable().onNext(it)
                },
                onError = {
                    errorLiveData.mutable().onNext(it)
                }
            )
    }

    private fun <T : Any> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T>
            ?: throw IllegalStateException("It is not Subject!")
    }
}