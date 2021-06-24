package com.itigraduationteam.madartask.ui.fragments.show.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itigraduationteam.madartask.databinding.LayoutListItemBinding
import com.itigraduationteam.madartask.model.User


class UserAdapter(val listener: (View, User, Int) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var data: List<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return  UserViewHolder(
            LayoutListItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(data[position])

    fun swapData(data: List<User>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun getUserAt(position: Int): User? {
        return data.get(position)
    }

    inner class UserViewHolder(val binding: LayoutListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) = with(itemView) {
            binding.nameTextView.text=item.mName
            binding.jobTitleTextView.text=item.mJopTitle
            binding.ageTextView.text=item.mAge.toString()
            binding.generTextView.text=item.mGender
            setOnClickListener {
                listener.invoke(it, item, adapterPosition)
            }
        }
    }
}