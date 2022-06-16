package by.yancheuski.githubusers.view.list

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.yancheuski.githubusers.app
import by.yancheuski.githubusers.databinding.ActivityMainBinding
import by.yancheuski.githubusers.domain.entities.UserEntity
import by.yancheuski.githubusers.view.OnClickUserListener

class MainActivity : AppCompatActivity(), UsersContract.View, OnClickUserListener {

    private lateinit var binding: ActivityMainBinding

    //    private val adapter = UserAdapter()
    private lateinit var adapter: UserAdapter

    private lateinit var presenter: UsersContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        presenter = getPresenter()
        presenter.attach(this)
        presenter.onRefresh()
    }

    private fun getPresenter() = lastCustomNonConfigurationInstance
            as? UsersContract.Presenter
        ?: UsersPresenter(app.usersRepo)

    override fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.listUsersRecyclerView.isVisible = !inProgress
    }

    override fun showUsers(users: List<UserEntity>) {
        adapter.setUsersData(users)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView() {
        binding.listUsersRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(this@MainActivity)
        binding.listUsersRecyclerView.adapter = adapter
    }

    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance(): UsersContract.Presenter {
        return presenter
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun onClickUser(user: UserEntity) {
        Toast.makeText(this@MainActivity, user.login, Toast.LENGTH_SHORT).show()
    }
}