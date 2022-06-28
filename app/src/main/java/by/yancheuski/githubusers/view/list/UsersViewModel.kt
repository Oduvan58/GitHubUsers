package by.yancheuski.githubusers.view.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.yancheuski.githubusers.domain.entities.UserEntity
import by.yancheuski.githubusers.domain.repos.UsersRepo

class UsersViewModel(private val usersRepo: UsersRepo) : ViewModel() {

    val usersLiveData: LiveData<List<UserEntity>> = MutableLiveData()
    val progressLiveData: LiveData<Boolean> = MutableLiveData()
    val errorLiveData: LiveData<Throwable> = MutableLiveData()

    fun loadData() {
        progressLiveData.mutable().postValue(true)
        usersRepo.getUsers(
            onSuccess = {
                progressLiveData.mutable().postValue(false)
                usersLiveData.mutable().postValue(it)
            },
            onError = {
                progressLiveData.mutable().postValue(false)
                errorLiveData.mutable().postValue(it)
            }
        )
    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw IllegalStateException("It is not MutableLiveData!")
    }
}