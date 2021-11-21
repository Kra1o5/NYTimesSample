package com.randomdroids.nytimes.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.randomdroids.nytimes.data.common.Response
import com.randomdroids.nytimes.data.common.ResultData
import com.randomdroids.nytimes.di.IoDispatcher
import com.randomdroids.nytimes.domain.Article
import com.randomdroids.nytimes.ui.main.MainViewModel
import com.randomdroids.nytimes.usecases.GetArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    @IoDispatcher private val requestDispatcher: CoroutineDispatcher,
    private val getArticleUseCase: GetArticleUseCase
) : ViewModel() {

    companion object {
        private val TAG = MainViewModel::class.qualifiedName
    }

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> get() = _articles

    private val _loading = MutableStateFlow(false)
    val loading: MutableStateFlow<Boolean> get() = _loading


    private val _navigateToArticleDetail = MutableStateFlow("")
    val navigateToArticleDetail: StateFlow<String> get() = _navigateToArticleDetail

    fun requestListArticles(type: String, publishDate: String, socialMedia: String) {
        viewModelScope.launch {
            _loading.value = true
            when (val result = getArticleUseCase.invoke(type, publishDate, socialMedia)) {
                is ResultData.Success -> onSuccessGetArticles(result.value)
                is ResultData.Failure -> onErrorGetArticles(result.throwable)
            }
            _loading.value = false
        }
    }

    /**
     * Function to handle when request succeeds
     *
     * @param article List of articles
     */
    private fun onSuccessGetArticles(article: Response<List<Article>>) {
        viewModelScope.launch(requestDispatcher) {
            article.getValue()?.let { _articles.emit(it) }
        }
    }

    /**
     * Function to handle when request fails
     *
     * @param throwable Exception
     */
    private fun onErrorGetArticles(throwable: Throwable) {
        Log.e(
            TAG,
            if (throwable.localizedMessage.isNullOrEmpty()) "Unexpected error" else throwable.localizedMessage
        )
    }

    fun onArticleClicked(article: Article) {
        _navigateToArticleDetail.value = article.url!!
    }
}
