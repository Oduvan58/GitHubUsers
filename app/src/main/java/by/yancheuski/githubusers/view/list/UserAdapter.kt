package by.yancheuski.githubusers.view.list

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.yancheuski.githubusers.domain.entities.UserEntity

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {

    init {
        setHasStableIds(true)
    }

    private var usersData = mutableListOf<UserEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun setUsersData(data: List<UserEntity>) {
        usersData.clear()
        usersData.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int) = getItem(position).id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(parent)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = usersData.size

    private fun getItem(pos: Int) = usersData[pos]
}