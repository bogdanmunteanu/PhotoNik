package com.death.photonik.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.death.photonik.R
import com.death.photonik.databinding.ActivityMainBinding
import com.death.photonik.ui.base.BaseActivity
import com.death.photonik.ui.home.adapter.PhotoAdapter
import com.death.photonik.utils.common.StaggeredGridItemDecoration
import javax.inject.Inject
import androidx.core.app.ActivityOptionsCompat
import android.content.Intent
import android.view.View
import com.death.photonik.ui.upload.UploadActivity


class HomeActivity : BaseActivity<ActivityMainBinding, HomeViewModel>() {

    override fun getLayoutId(): Int  = R.layout.activity_main

    @Inject
    lateinit var stLayoutManager: StaggeredGridLayoutManager

    @Inject
    lateinit var adapter: PhotoAdapter

    val itemDecoration = StaggeredGridItemDecoration(16)

    override fun setupView(savedInstanceState: Bundle?) {
        binding.vm = viewModel
        binding.photos.layoutManager = stLayoutManager
        binding.photos.adapter = adapter
        binding.photos.addItemDecoration(itemDecoration)

        binding.addPhoto.setOnClickListener {
            val intent = Intent(this, UploadActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, binding.addPhoto as View, "add")
            startActivity(intent, options.toBundle())
        }
    }


    override fun setupObservers() {
        super.setupObservers()
        viewModel.onNetworkResponse.observe(this, Observer {
            it.getIfNotHandled()?.run {
                adapter.appendData(photos)
            }
        })
    }
}
