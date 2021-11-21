package com.randomdroids.nytimes.ui.detail

import com.randomdroids.nytimes.data.repository.NYTimesRepository
import com.randomdroids.nytimes.usecases.GetArticleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DetailFragmentModule {
    @Provides
    @ViewModelScoped
    fun getArticlesUseCaseProvider(nyTimesRepository: NYTimesRepository) =
        GetArticleUseCase(nyTimesRepository)
}