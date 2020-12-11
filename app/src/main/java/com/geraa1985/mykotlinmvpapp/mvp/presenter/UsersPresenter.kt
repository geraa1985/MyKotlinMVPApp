package com.geraa1985.mykotlinmvpapp.mvp.presenter

import com.geraa1985.mykotlinmvpapp.MyApp
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import com.geraa1985.mykotlinmvpapp.mvp.model.repository.IUsersRepo
import com.geraa1985.mykotlinmvpapp.mvp.presenter.list.user.IUserListPresenter
import com.geraa1985.mykotlinmvpapp.mvp.view.IUsersView
import com.geraa1985.mykotlinmvpapp.mvp.view.list.userItem.IUserItemView
import com.geraa1985.mykotlinmvpapp.navigation.Screens
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UsersPresenter(
    private val uiScheduler: Scheduler,
    private val usersRepo: IUsersRepo
) :
    MvpPresenter<IUsersView>() {

    @Inject
    lateinit var router: Router

    init {
        MyApp.instance.appGraph.inject(this)
    }

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
            view.setAvatar(user.avatarUrl)
        }
    }

    val usersListPresenter = UsersListPresenter()
    private val compositeDisposable = CompositeDisposable()
    private val subject: PublishSubject<GithubUser> = PublishSubject.create()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initRvUsers()
        loadData()

        subject.observeOn(Schedulers.io()).subscribe { gitHubUser ->
            usersRepo.putUser(gitHubUser)
        }

        usersListPresenter.itemClickListener = {
            val user = usersListPresenter.users[it.pos]
            subject.onNext(user)
            router.navigateTo(Screens.userScreen(user))
        }
    }

    private fun loadData() {
        val disposable1 = usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({
                usersListPresenter.users.addAll(it)
                viewState.updateUsersList()
            }, { error ->
                error.message?.let {
                    viewState.showError(it)
                }
            })
        compositeDisposable.add(disposable1)
    }

    fun searchUser(login: String?) {
        login?.let { userLogin ->
            val disposable2 = usersRepo.getUser(userLogin)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user ->
                    router.navigateTo(Screens.userScreen(user))
                }, { error ->
                    error.message?.let { viewState.showError(it) }
                })
            compositeDisposable.add(disposable2)
        }
    }

    fun searchUsers(login: String?) {
        login?.let { userLogin ->
            if (userLogin.isNotEmpty()) {
                val disposable3 =
                    usersRepo.searchUsers(userLogin)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ users ->
                            usersListPresenter.users.clear()
                            usersListPresenter.users.addAll(users)
                            viewState.updateUsersList()
                        }, { error ->
                            error.message?.let { viewState.showError(it) }
                        })
                compositeDisposable.add(disposable3)
            }
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

}