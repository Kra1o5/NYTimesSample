package com.randomdroids.nytimes.ui.browse

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class BrowserViewModel @Inject constructor(
) : ViewModel() {

    companion object {
        private val TAG = BrowserViewModel::class.qualifiedName
    }

    private val _loading = MutableStateFlow(false)
    val loading: MutableStateFlow<Boolean> get() = _loading
}