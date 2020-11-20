package com.geraa1985.mykotlinmvpapp.mvp.presenter

import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import com.geraa1985.mykotlinmvpapp.mvp.model.repository.GithubUsersRepo
import com.geraa1985.mykotlinmvpapp.mvp.presenter.list.user.IUserListPresenter
import com.geraa1985.mykotlinmvpapp.mvp.view.IUsersView
import com.geraa1985.mykotlinmvpapp.mvp.view.list.userItem.IUserItemView
import com.geraa1985.mykotlinmvpapp.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router): MvpPresenter<IUsersView>() {

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
            router.navigateTo(Screens.userScreen(usersListPresenter.users[it.pos]))
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}