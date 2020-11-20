package com.geraa1985.mykotlinmvpapp.mvp.presenter.list.base

import com.geraa1985.mykotlinmvpapp.mvp.view.list.baseItem.IItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}
