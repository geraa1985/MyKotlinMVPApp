package com.geraa1985.mykotlinmvpapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.geraa1985.mykotlinmvpapp.R
import com.geraa1985.mykotlinmvpapp.databinding.ItemRvReposBinding
import com.geraa1985.mykotlinmvpapp.mvp.presenter.list.repo.IRepoListPresenter
import com.geraa1985.mykotlinmvpapp.mvp.view.list.repoItem.IRepoItemView

class RepoRVAdapter(private val presenter: IRepoListPresenter) :
    RecyclerView.Adapter<RepoRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRvReposBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.getItem().setOnClickListener {
            presenter.itemClickListener?.invoke(holder)
        }
        presenter.bindView(holder)
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.getItem().context, R.anim.rv_repos_anims)
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }

    inner class ViewHolder(private val binding: ItemRvReposBinding) :
        RecyclerView.ViewHolder(binding.root), IRepoItemView {

        override var pos = 0

        override fun setName(name: String) {
            binding.tvName.text = name
        }

        fun getItem() = binding.root
    }
}