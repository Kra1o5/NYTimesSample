package com.randomdroids.nytimes.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    companion object {
        private val TAG = MainViewModel::class.qualifiedName
    }

    private val _loading = MutableStateFlow(false)
    val loading: MutableStateFlow<Boolean> get() = _loading

    private val _navigateToArticleDetail = MutableStateFlow(false)
    val navigateToArticleDetail: StateFlow<Boolean> get() = _navigateToArticleDetail


    fun dataFilter() {
        viewModelScope.launch {
            _loading.value = true
            _navigateToArticleDetail.value = true
            _loading.value = false
        }
    }
}