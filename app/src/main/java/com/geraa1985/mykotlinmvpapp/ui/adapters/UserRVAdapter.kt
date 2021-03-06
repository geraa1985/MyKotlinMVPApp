package com.geraa1985.mykotlinmvpapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.geraa1985.mykotlinmvpapp.MyApp
import com.geraa1985.mykotlinmvpapp.R
import com.geraa1985.mykotlinmvpapp.databinding.ItemRvUsersBinding
import com.geraa1985.mykotlinmvpapp.mvp.model.repository.ILoadImage
import com.geraa1985.mykotlinmvpapp.mvp.presenter.list.user.IUserListPresenter
import com.geraa1985.mykotlinmvpapp.mvp.view.list.userItem.IUserItemView
import javax.inject.Inject

class UserRVAdapter(
    private val presenter: IUserListPresenter
): RecyclerView.Adapter<UserRVAdapter.ViewHolder>() {

    @Inject
    lateinit var loadImg: ILoadImage<ImageView, RequestOptions>

    init {
        MyApp.instance.appGraph.inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRvUsersBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.getItem().setOnClickListener {
            presenter.itemClickListener?.invoke(holder)
        }
        presenter.bindView(holder)
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.getItem().context, R.anim.rv_users_anims)
    }

    inner class ViewHolder(private val binding: ItemRvUsersBinding) : RecyclerView.ViewHolder(binding.root),
        IUserItemView {

        override var pos = 0

        override fun setLogin(login: String) {
            binding.tvLogin.text = login
        }

        override fun setAvatar(url: String?) {
            url?.let { loadImg.loadInto(it, binding.ava, RequestOptions().circleCrop()) }
        }

        fun getItem() = binding.root
    }
}
