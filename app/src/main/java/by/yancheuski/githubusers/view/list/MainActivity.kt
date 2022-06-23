package by.yancheuski.githubusers.view.list

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.yancheuski.githubusers.app
import by.yancheuski.githubusers.databinding.ActivityMainBinding
import by.yancheuski.githubusers.domain.entities.UserEntity
import by.yancheuski.githubusers.view.OnClickUserListener
import by.yancheuski.githubusers.view.details.USER_EXTRA_KEY
import by.yancheuski.githubusers.view.details.UserProfileActivity

class MainActivity : AppCompatActivity(), OnClickUserListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter

    private lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        initViewModel()
        viewModel.loadData()
    }

    private fun initViewModel() {
        viewModel = getViewModel()
        viewModel.progressLiveData.observe(this) { showProgress(it) }
        viewModel.usersLiveData.observe(this) { showUsers(it) }
        viewModel.errorLiveData.observe(this) { showError(it) }
    }

    private fun getViewModel(): UsersViewModel {
        return lastCustomNonConfigurationInstance as? UsersViewModel
            ?: UsersViewModel(app.usersRepo)
    }

    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance(): UsersViewModel {
        return viewModel
    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.listUsersRecyclerView.isVisible = !inProgress
    }

    private fun showUsers(users: List<UserEntity>) {
        adapter.setUsersData(users)
    }

    private fun showError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView() {
        binding.listUsersRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(this@MainActivity)
        binding.listUsersRecyclerView.adapter = adapter
    }

    override fun onClickUser(user: UserEntity) {
        startActivity(Intent(this@MainActivity, UserProfileActivity::class.java)
            .apply {
                putExtra(USER_EXTRA_KEY, user.login)
            })
    }
}