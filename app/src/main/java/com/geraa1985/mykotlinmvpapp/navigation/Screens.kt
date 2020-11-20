package com.geraa1985.mykotlinmvpapp.navigation

import androidx.fragment.app.Fragment
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import com.geraa1985.mykotlinmvpapp.ui.fragments.UserFragment
import com.geraa1985.mykotlinmvpapp.ui.fragments.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens(private val fragment: Fragment): SupportAppScreen() {

    override fun getFragment() = fragment

    companion object {
        fun usersScreen() = Screens(UsersFragment())
        fun userScreen(user: GithubUser) = Screens(UserFragment.newInstance(user))
    }
}