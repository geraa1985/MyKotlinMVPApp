package com.geraa1985.mykotlinmvpapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geraa1985.mykotlinmvpapp.databinding.ItemRvBinding
import com.geraa1985.mykotlinmvpapp.presenter.IUserListPresenter
import com.geraa1985.mykotlinmvpapp.view.IUserItemView

class UsersRVAdapter(private val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRvBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.getItem().setOnClickListener {
            presenter.itemClickListener?.invoke(holder)
        }
        presenter.bindView(holder)
    }

    class ViewHolder(private val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root), IUserItemView {

        override var pos = 0

        override fun setLogin(login: String) {
            binding.tvLogin.text = login
        }

        fun getItem() = binding.root
    }
}
