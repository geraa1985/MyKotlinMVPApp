package com.geraa1985.mykotlinmvpapp.mvp.view.list.userItem

import com.geraa1985.mykotlinmvpapp.mvp.view.list.baseItem.IItemView

interface IUserItemView : IItemView {
    fun setLogin(login: String)
    fun setAvatar(url: String?)
}