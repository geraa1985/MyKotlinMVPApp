package com.geraa1985.mykotlinmvpapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.geraa1985.mykotlinmvpapp.R
import com.geraa1985.mykotlinmvpapp.databinding.ItemRvBinding
import com.geraa1985.mykotlinmvpapp.mvp.model.ILoadImage
import com.geraa1985.mykotlinmvpapp.mvp.presenter.list.user.IUserListPresenter
import com.geraa1985.mykotlinmvpapp.mvp.view.list.userItem.IUserItemView

class UsersRVAdapter(
    private val presenter: IUserListPresenter,
    private val loadImg: ILoadImage<ImageView>
) :
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
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.getItem().context, R.anim.rv_anims)
    }

    inner class ViewHolder(private val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root),
        IUserItemView {

        override var pos = 0

        override fun setLogin(login: String) {
            binding.tvLogin.text = login
        }

        override fun setAvatar(url: String?) {
            url?.let { loadImg.loadInto(it, binding.ava) }
        }

        fun getItem() = binding.root
    }
}
