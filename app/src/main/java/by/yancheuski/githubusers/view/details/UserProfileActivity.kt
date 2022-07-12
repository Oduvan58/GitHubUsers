package by.yancheuski.githubusers.view.details

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.yancheuski.githubusers.app
import by.yancheuski.githubusers.databinding.ActivityUserProfileBinding
import by.yancheuski.githubusers.domain.entities.UserProfileEntity
import coil.api.load
import io.reactivex.rxjava3.disposables.CompositeDisposable

const val USER_EXTRA_KEY = "USER_EXTRA_KEY"

class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserProfileBinding
    private var login = ""
    private lateinit var viewModel: UserProfileViewModel
    private var viewModelDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getUser()
        initViewModel()
        viewModel.onRefresh(login)
    }

    private fun initViewModel() {
        viewModel = getViewModel()
        viewModelDisposable.addAll(
            viewModel.userLiveData.subscribe { showUser(it) },
            viewModel.errorLiveData.subscribe { showError(it) }
        )
    }

    private fun getViewModel(): UserProfileViewModel {
        return lastCustomNonConfigurationInstance as? UserProfileViewModel
            ?: UserProfileViewModel(app.usersRepo)
    }

    private fun showUser(user: UserProfileEntity) {
        with(binding) {
            avatarUserProfileImageView.load(user.avatarUrl)
            loginUserProfileTextView.text = user.login
            idUserProfileTextView.text = user.id.toString()
            nameUserProfileTextView.text = user.name
            companyUserProfileTextView.text = user.company
            locationUserProfileTextView.text = user.location
        }
    }

    private fun showError(error: Throwable) {
        if (error.message != null) {
            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getUser() {
        val infoUser = intent.extras
        if (infoUser != null) {
            login = infoUser.getString(USER_EXTRA_KEY, "")
            binding.loginUserProfileTextView.text = login
        }
    }

    override fun onDestroy() {
        viewModelDisposable.dispose()
        super.onDestroy()
    }
}