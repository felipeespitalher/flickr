package com.tigerspike.ui.commons

import androidx.lifecycle.MutableLiveData

class SingleEventLiveData<T> : MutableLiveData<Event<T>>() {

    fun set(value: T) {
        setValue(Event(value))
    }

}