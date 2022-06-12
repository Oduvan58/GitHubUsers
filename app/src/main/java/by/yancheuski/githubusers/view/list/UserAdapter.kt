package by.yancheuski.githubusers.view.list

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.yancheuski.githubusers.domain.UserEntity

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private var data = mutableListOf<UserEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: List<UserEntity>) {
        data.clear()
        data.addAll(users)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int) = getItem(position).id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(parent)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = data.size

    private fun getItem(pos: Int) = data[pos]
}