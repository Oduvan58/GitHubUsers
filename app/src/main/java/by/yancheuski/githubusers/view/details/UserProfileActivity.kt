package by.yancheuski.githubusers.view.details

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.yancheuski.githubusers.app
import by.yancheuski.githubusers.databinding.ActivityUserProfileBinding
import by.yancheuski.githubusers.domain.entities.UserEntity
import by.yancheuski.githubusers.presenter.UserContract
import by.yancheuski.githubusers.presenter.UserPresenter
import coil.api.load

const val USER_EXTRA_KEY = "USER_EXTRA_KEY"

class UserProfileActivity : AppCompatActivity(), UserContract.View {

    private lateinit var binding: ActivityUserProfileBinding
    private var login = ""
    private lateinit var presenter: UserContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUser()

        presenter = UserPresenter(app.usersRepo)
        presenter.attach(this)
        presenter.onRefresh(login)
    }

    override fun showUser(user: UserEntity) {
        with(binding) {
            avatarUserProfileImageView.load(user.avatarUrl)
            loginUserProfileTextView.text = user.login
            idUserProfileTextView.text = user.id.toString()
        }
    }

    override fun showError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    private fun getUser() {
        val infoUser = intent.extras
        if (infoUser != null) {
            login = infoUser.getString(USER_EXTRA_KEY, "")
            binding.loginUserProfileTextView.text = login
        }
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }
}