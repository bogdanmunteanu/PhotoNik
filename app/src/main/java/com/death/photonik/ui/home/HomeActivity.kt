package com.death.photonik.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.death.photonik.R
import com.death.photonik.databinding.ActivityMainBinding
import com.death.photonik.ui.base.BaseActivity
import timber.log.Timber

class HomeActivity : BaseActivity<ActivityMainBinding, HomeViewModel>() {

    override fun getLayoutId(): Int  = R.layout.activity_main

    override fun setupView(savedInstanceState: Bundle?) {
        viewModel.onNetworkResponse.observe(this, Observer {
            Timber.e(it.toString())
        })
    }

}
