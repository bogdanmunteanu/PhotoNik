package com.death.photonik.utils.common

import java.util.concurrent.atomic.AtomicBoolean

open class Event<out T>(private val content: T){

    private var hasBeenHandled = AtomicBoolean(false)

    fun getIfNotHandled() : T? = if (hasBeenHandled.getAndSet(true)) null else content

    fun peek():T = content

}