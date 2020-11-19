package com.geraa1985.mykotlinmvpapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geraa1985.mykotlinmvpapp.R
import com.geraa1985.mykotlinmvpapp.presenter.IUserListPresenter
import com.geraa1985.mykotlinmvpapp.view.IUserItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_rv.*

class UsersRVAdapter(private val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false))

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener {
            presenter.itemClickListener?.invoke(holder)
        }
        presenter.bindView(holder)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer, IUserItemView {
        override var pos = -1

        override fun setLogin(login: String) = containerView.run {
            tv_login.text = login
        }
    }
}