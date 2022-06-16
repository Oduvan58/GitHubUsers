package by.yancheuski.githubusers.view.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.yancheuski.githubusers.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}