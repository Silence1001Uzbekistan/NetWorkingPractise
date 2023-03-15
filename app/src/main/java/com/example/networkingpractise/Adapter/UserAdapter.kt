package com.example.networkingpractise.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.networkingpractise.databinding.ItemUserBinding
import com.example.networkingpractise.models.UserItem

class UserAdapter(var list: List<UserItem>) : RecyclerView.Adapter<UserAdapter.vh>() {

    inner class vh(var itemUserBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root) {

        fun onBind(user: UserItem) {

            itemUserBinding.tv1.text = user.name
            itemUserBinding.tv2.text = user.username

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vh {

        return vh(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun getItemCount(): Int {

        return list.size

    }

    override fun onBindViewHolder(holder: vh, position: Int) {

        holder.onBind(list[position])

    }


}