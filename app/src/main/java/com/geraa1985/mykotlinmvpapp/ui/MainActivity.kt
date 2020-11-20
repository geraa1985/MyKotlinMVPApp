package com.geraa1985.mykotlinmvpapp.ui

import android.os.Bundle
import com.geraa1985.mykotlinmvpapp.databinding.ActivityMainBinding
import com.geraa1985.mykotlinmvpapp.model.Model
import com.geraa1985.mykotlinmvpapp.presenter.Presenter
import com.geraa1985.mykotlinmvpapp.view.View
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

private lateinit var binding: ActivityMainBinding

class MainActivity : MvpAppCompatActivity(), View, android.view.View.OnClickListener {

    private val presenter by moxyPresenter { Presenter(Model()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener(this)
        binding.button2.setOnClickListener(this)
        binding.button3.setOnClickListener(this)
    }

    override fun onClick(v: android.view.View?) {
        when (v) {
            binding.button1 -> presenter.clickOnButton1()
            binding.button2 -> presenter.clickOnButton2()
            binding.button3 -> presenter.clickOnButton3()
        }
    }

    override fun setTextForButton1(value: String) {
        binding.button1.text = value
    }

    override fun setTextForButton2(value: String) {
        binding.button2.text = value
    }

    override fun setTextForButton3(value: String) {
        binding.button3.text = value
    }
}