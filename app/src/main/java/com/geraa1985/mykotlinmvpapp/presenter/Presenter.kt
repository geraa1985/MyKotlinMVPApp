package com.geraa1985.mykotlinmvpapp.presenter

import com.geraa1985.mykotlinmvpapp.model.GithubUser
import com.geraa1985.mykotlinmvpapp.model.GithubUsersRepo
import com.geraa1985.mykotlinmvpapp.view.IUserItemView
import com.geraa1985.mykotlinmvpapp.view.View
import moxy.MvpPresenter

class Presenter(private val usersRepo: GithubUsersRepo): MvpPresenter<View>() {

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = {
            //TODO: переход на экран пользователя

        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }
}
