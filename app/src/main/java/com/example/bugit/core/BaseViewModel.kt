package com.example.bugit.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<State, Event>: ViewModel(){
    protected abstract val privateState: MutableStateFlow<State>
    val state get() = privateState.asStateFlow()
    abstract fun onEvent(event: Event)
}