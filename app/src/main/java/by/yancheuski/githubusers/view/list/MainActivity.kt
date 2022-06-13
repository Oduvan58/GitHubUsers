package by.yancheuski.githubusers.view.list

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.yancheuski.githubusers.app
import by.yancheuski.githubusers.databinding.ActivityMainBinding
import by.yancheuski.githubusers.domain.entities.UserEntity
import by.yancheuski.githubusers.domain.repos.UsersRepo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UserAdapter()
    private val userRepo: UsersRepo by lazy { app.usersRepo }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        initRecyclerView()
    }

    private fun loadData() {
        showProgress(true)
        userRepo.getUsers(
            onSuccess = {
                showProgress(false)
                onDataLoaded(it)
            },
            onError = {
                showProgress(false)
                onError(it)
            }
        )
    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.listUsersRecyclerView.isVisible = !inProgress
    }

    private fun onDataLoaded(data: List<UserEntity>) {
        adapter.setUsersData(data)
    }

    private fun onError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView() {
        binding.listUsersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.listUsersRecyclerView.adapter = adapter
    }
}