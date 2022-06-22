package by.yancheuski.githubusers.view.list

import by.yancheuski.githubusers.domain.entities.UserEntity
import by.yancheuski.githubusers.domain.repos.UsersRepo

class UsersPresenter(private val userRepo: UsersRepo) : UsersContract.Presenter {

    private var view: UsersContract.View? = null
    private var usersList: List<UserEntity>? = null
    private var inProgress: Boolean = false

    override fun attach(view: UsersContract.View) {
        this.view = view
        view.showProgress(inProgress)
        usersList?.let { view.showUsers(it) }
    }

    override fun detach() {
        view = null
    }

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        view?.showProgress(true)
        inProgress = true
        userRepo.getUsers(
            onSuccess = {
                view?.showProgress(false)
                view?.showUsers(it)
                usersList = it
                inProgress = false
            },
            onError = {
                view?.showProgress(false)
                view?.showError(it)
                inProgress = false
            }
        )
    }
}