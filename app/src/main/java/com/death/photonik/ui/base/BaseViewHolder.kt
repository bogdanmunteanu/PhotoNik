package com.death.photonik.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<B: ViewDataBinding, T:Any, VM: BaseItemViewModel<T>>(
    protected val binding:B,
    protected val viewModel:VM
): RecyclerView.ViewHolder(binding.root){

    abstract fun bindViewModel(viewModel: VM)

    open fun bind(data: T){
        viewModel.updateData(data)
        bindViewModel(viewModel)
    }

}