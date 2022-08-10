package by.yancheuski.githubusers.presenter

import by.yancheuski.githubusers.domain.entities.UserEntity

interface UserContract {

    interface View {
        fun showUser(user: UserEntity)
        fun showError(error: Throwable)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onRefresh(login: String)
    }
}