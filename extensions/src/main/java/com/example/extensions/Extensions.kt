package com.example.extensions

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Extensions {

    companion object {

        inline fun <reified T> initOnce(): ReadWriteProperty<Any, T?> = InitOnceProperty()
    }

    class InitOnceProperty<T> : ReadWriteProperty<Any, T?> {

        private var value: Any? = null

        @Suppress("UNCHECKED_CAST")
        override fun getValue(thisRef: Any, property: KProperty<*>): T? {
            return if (value == null) {
                null
            } else {
                value as T
            }
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
            if (this.value != null) {
                return
            }
            this.value = value
        }
    }

    sealed class Resource<T>(val data: T? = null, val message: String? = null) {
        class Success<T>(data: T) : Resource<T>(data)
        class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
        class Loading<T>(data: T? = null) : Resource<T>(data)
    }
}