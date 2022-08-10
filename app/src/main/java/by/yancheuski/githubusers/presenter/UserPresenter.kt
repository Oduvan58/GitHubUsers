package by.yancheuski.githubusers.presenter

import by.yancheuski.githubusers.domain.repos.UsersRepo

class UserPresenter(private val userProfileRepo: UsersRepo) : UserContract.Presenter {

    private var view: UserContract.View? = null
    private lateinit var login: String

    override fun attach(view: UserContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun onRefresh(login: String) {
        this.login = login
        loadUser()
    }

    private fun loadUser() {
        userProfileRepo.getUser(
            login = login,
            onSuccess = {
                view?.showUser(it)
            },
            onError = {
                view?.showError(it)
            }
        )
    }
}