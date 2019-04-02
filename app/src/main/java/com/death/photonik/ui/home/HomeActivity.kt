package com.death.photonik.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import com.death.photonik.R
import com.death.photonik.databinding.ActivityMainBinding
import com.death.photonik.ui.base.BaseActivity
import timber.log.Timber

class HomeActivity : BaseActivity<ActivityMainBinding, HomeViewModel>() {

    override fun getLayoutId(): Int  = R.layout.activity_main

    override fun setupView(savedInstanceState: Bundle?) {

        binding.vm = viewModel

        viewModel.onNetworkResponse.observe(this, Observer {
            it.getIfNotHandled()?.run {
                Timber.e(this.toString())
            }
        })

    }

}
