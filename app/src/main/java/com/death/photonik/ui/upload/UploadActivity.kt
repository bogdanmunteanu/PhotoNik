package com.death.photonik.ui.upload

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.death.photonik.R
import com.death.photonik.databinding.ActivityUploadBinding
import com.death.photonik.ui.base.BaseActivity

class UploadActivity : BaseActivity<ActivityUploadBinding, UploadVModel>(){
    override fun getLayoutId(): Int = R.layout.activity_upload

    override fun setupView(savedInstanceState: Bundle?) {

        binding.vm = viewModel

        viewModel.getMessage().observe(this, Observer {
            it.data?.let {value->
                Toast.makeText(this@UploadActivity,value,Toast.LENGTH_SHORT).show()
            }
        })
    }
}