package com.geraa1985.mykotlinmvpapp.presenter

import com.geraa1985.mykotlinmvpapp.view.IItemView
import com.geraa1985.mykotlinmvpapp.view.IUserItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<IUserItemView>