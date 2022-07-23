package by.yancheuski.githubusers.domain.entities

import com.google.gson.annotations.SerializedName

data class UserProfileEntity(
    val login: String,
    val id: Long,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val company: String,
    val location: String,
    val name: String,
)
