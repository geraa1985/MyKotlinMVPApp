package com.geraa1985.mykotlinmvpapp.mvp.presenter

import com.geraa1985.mykotlinmvpapp.MyApp
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.UserRepo
import com.geraa1985.mykotlinmvpapp.mvp.model.repository.IReposRepo
import com.geraa1985.mykotlinmvpapp.mvp.presenter.list.repo.IRepoListPresenter
import com.geraa1985.mykotlinmvpapp.mvp.view.IUserView
import com.geraa1985.mykotlinmvpapp.mvp.view.list.repoItem.IRepoItemView
import com.geraa1985.mykotlinmvpapp.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UserPresenter(
    private val user: GithubUser?,
    private val uiScheduler: Scheduler,
    private val usersRepo: IReposRepo
    ): MvpPresenter<IUserView>() {

    @Inject
    lateinit var router: Router

    init {
        MyApp.instance.appGraph.inject(this)
    }

    class ReposListPresenter : IRepoListPresenter {

        val repos = mutableListOf<UserRepo>()

        override var itemClickListener: ((IRepoItemView) -> Unit)? = null

        override fun getCount() = repos.size

        override fun bindView(view: IRepoItemView) {
            val repo = repos[view.pos]
            view.setName(repo.name)
        }
    }

    val reposListPresenter = ReposListPresenter()
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initRvRepos()

        showLogin()
        showAvatar()
        loadData()

        reposListPresenter.itemClickListener = {
            router.navigateTo(Screens.repoScreen(reposListPresenter.repos[it.pos]))
        }
    }

    private fun showLogin() {
        user?.let {
            viewState.showLogin(it.login)
        }
    }

    private fun showAvatar() {
        user?.avatarUrl?.let {
            viewState.showAvatar(it)
        }
    }

    private fun loadData() {
        user?.let {
            val disposable1 = usersRepo.getRepos(user)
                .observeOn(uiScheduler)
                .subscribe({
                    reposListPresenter.repos.addAll(it)
                    viewState.updateReposList()
                }, { error ->
                    error.message?.let {
                        viewState.showError(it)
                    }
                })
            compositeDisposable.add(disposable1)
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