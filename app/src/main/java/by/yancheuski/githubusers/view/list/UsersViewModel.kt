package by.yancheuski.githubusers.view.list

import androidx.lifecycle.ViewModel
import by.yancheuski.githubusers.domain.entities.UserEntity
import by.yancheuski.githubusers.domain.repos.UsersRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

class UsersViewModel(private val usersRepo: UsersRepo) : ViewModel() {

    val usersLiveData: Observable<List<UserEntity>> = BehaviorSubject.create()
    val progressLiveData: Observable<Boolean> = BehaviorSubject.create()
    val errorLiveData: Observable<Throwable> = BehaviorSubject.create()

    fun loadData() {
        progressLiveData.mutable().onNext(true)
        usersRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    progressLiveData.mutable().onNext(false)
                    usersLiveData.mutable().onNext(it)
                },
                onError = {
                    progressLiveData.mutable().onNext(false)
                    errorLiveData.mutable().onNext(it)
                }
            )
    }

    private fun <T : Any> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T>
            ?: throw IllegalStateException("It is not Subject!")
    }
}