package by.yancheuski.githubusers.view

import by.yancheuski.githubusers.domain.entities.UserEntity

interface OnClickUserListener {

    fun onClickUser(user: UserEntity)
}