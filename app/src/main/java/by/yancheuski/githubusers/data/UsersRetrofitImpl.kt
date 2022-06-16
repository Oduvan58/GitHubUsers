package by.yancheuski.githubusers.data

import by.yancheuski.githubusers.domain.entities.UserEntity
import by.yancheuski.githubusers.domain.repos.GitHubUsersApi
import by.yancheuski.githubusers.domain.repos.UsersRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersRetrofitImpl : UsersRepo {

    private val baseUrl = "https://api.github.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var usersApi: GitHubUsersApi = retrofit.create(GitHubUsersApi::class.java)

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        usersApi.getListOfUsers().enqueue(object : Callback<List<UserEntity>> {
            override fun onResponse(
                call: Call<List<UserEntity>>,
                response: Response<List<UserEntity>>,
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    onSuccess.invoke(body)
                } else {
                    onError?.invoke(Throwable())
                }
            }

            override fun onFailure(call: Call<List<UserEntity>>, t: Throwable) {
                onError?.let { it(t) }
            }
        })
    }

    override fun getUser(
        login: String,
        onSuccess: (user: UserEntity) -> Unit,
        onError: ((Throwable) -> Unit)?,
    ) {
        usersApi.getUserGit(login).enqueue(object : Callback<UserEntity> {
            override fun onResponse(call: Call<UserEntity>, response: Response<UserEntity>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    onSuccess.invoke(body)
                } else {
                    onError?.invoke(Throwable())
                }
            }

            override fun onFailure(call: Call<UserEntity>, t: Throwable) {
                onError?.let { it(t) }
            }
        })
    }
}