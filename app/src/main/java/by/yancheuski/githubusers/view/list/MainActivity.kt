package by.yancheuski.githubusers.view.list

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.yancheuski.githubusers.databinding.ActivityMainBinding
import by.yancheuski.githubusers.domain.entities.UserEntity
import by.yancheuski.githubusers.view.OnClickUserListener
import by.yancheuski.githubusers.view.details.USER_EXTRA_KEY
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), OnClickUserListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private val viewModel: UsersViewModel by viewModel()
    private var viewModelDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        initViewModel()
        showProgress(true)
        showListOfUsers()
    }

    private fun showListOfUsers() {
        binding.loadButton.buttonObservable.subscribe { viewModel.loadData() }
    }

    private fun initViewModel() {
        viewModelDisposable.addAll(
            viewModel.progressLiveData.subscribe { showProgress(it) },
            viewModel.usersLiveData.subscribe { showUsers(it) },
            viewModel.errorLiveData.subscribe { showError(it) }
        )
    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.progressBar.isVisible = !inProgress
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
        startActivity(Intent("show user")
            .apply {
                putExtra(USER_EXTRA_KEY, user.login)
            })
    }

    override fun onDestroy() {
        viewModelDisposable.dispose()
        super.onDestroy()
    }
}