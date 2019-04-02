package com.death.photonik.ui.base

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.death.instagram.ui.base.BaseViewModel
import com.death.photonik.ui.di.ActivityInjector
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseActivity<B: ViewDataBinding, VM: BaseViewModel>: AppCompatActivity() {
    companion object {
        const val DEFAULT_BUNDLE_KEY = "DEFAULT_BUNDLE_KEY"
    }

    @Inject
    lateinit var viewModel:VM

    lateinit var binding: B

    /**
     * Here @LayoutRes expects method to return int value, generally a layout resource
     */
    @LayoutRes
    protected abstract fun getLayoutId():Int

    protected abstract fun setupView(savedInstanceState:Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        ActivityInjector.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
        setupView(savedInstanceState)
        viewModel.onViewCreated()
        setupObservers()
    }
    
    fun setupObservers(){
        viewModel.getMessage().observe(this, Observer {
            it.data?.run {
                showMessage(this)
            }
        })

        viewModel.getMessageStringId().observe(this, Observer {
            it.data?.run {
                showMessage(this)
            }
        })
    }

    fun showMessage(message: String) = Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

    fun showMessage(@StringRes resId:Int) = showMessage(getString(resId))

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission:String):Boolean =
            Build.VERSION.SDK_INT<Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED

    open fun goBack() = onBackPressed()

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount>0)
            supportFragmentManager.popBackStackImmediate()
        else
            super.onBackPressed()
    }
}