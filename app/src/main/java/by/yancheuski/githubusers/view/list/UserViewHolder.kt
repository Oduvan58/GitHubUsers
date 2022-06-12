package by.yancheuski.githubusers.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.yancheuski.githubusers.R
import by.yancheuski.githubusers.databinding.ItemUserBinding
import by.yancheuski.githubusers.domain.UserEntity
import coil.api.load

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {

    private val binding = ItemUserBinding.bind(itemView)

    fun bind(user: UserEntity) {
        with(binding) {
            loginUserCardViewTextView.text = user.login
            idUserCardViewTextView.text = user.id.toString()
            avatarUserCardViewImageView.load(user.avatarUrl)
        }
    }
}