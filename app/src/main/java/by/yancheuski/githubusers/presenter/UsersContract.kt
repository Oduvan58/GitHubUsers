package by.yancheuski.githubusers.presenter

import by.yancheuski.githubusers.domain.entities.UserEntity

interface UsersContract {

    interface View {
        fun showUsers(users: List<UserEntity>)
        fun showProgress(inProgress: Boolean)
        fun showError(error: Throwable)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onRefresh()
    }
}