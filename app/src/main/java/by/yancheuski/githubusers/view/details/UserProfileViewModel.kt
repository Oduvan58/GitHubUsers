package by.yancheuski.githubusers.view.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.yancheuski.githubusers.domain.entities.UserEntity
import by.yancheuski.githubusers.domain.repos.UsersRepo

class UserProfileViewModel(private val userRepo: UsersRepo) : ViewModel() {

    val userLiveData: LiveData<UserEntity> = MutableLiveData()
    val errorLiveData: LiveData<Throwable> = MutableLiveData()
    private lateinit var login: String

    fun onRefresh(login: String) {
        this.login = login
        loadUser()
    }

    private fun loadUser() {
        userRepo.getUser(
            login = login,
            onSuccess = {
                userLiveData.mutable().postValue(it)
            },
            onError = {
                errorLiveData.mutable().postValue(it)
            }
        )
    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw IllegalStateException("It is not MutableLiveData!")
    }
}