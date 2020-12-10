package com.geraa1985.mykotlinmvpapp.ui.activities

import android.os.Bundle
import com.geraa1985.mykotlinmvpapp.MyApp
import com.geraa1985.mykotlinmvpapp.databinding.ActivityMainBinding
import com.geraa1985.mykotlinmvpapp.mvp.presenter.MainPresenter
import com.geraa1985.mykotlinmvpapp.mvp.view.IMainView
import com.geraa1985.mykotlinmvpapp.ui.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(), IMainView {

    private lateinit var binding: ActivityMainBinding

    private val navigatorHolder = MyApp.instance.navigatorHolder
    private val navigator by lazy {
        SupportAppNavigator(this, supportFragmentManager, binding.container.id)
    }
    private val presenter by moxyPresenter { MainPresenter(MyApp.instance.router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }

}