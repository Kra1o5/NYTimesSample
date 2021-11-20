package com.randomdroids.nytimes.di

import com.randomdroids.nytimes.data.server.NYTimesServerDataSource
import com.randomdroids.nytimes.data.server.NYTimesServerService
import com.randomdroids.nytimes.data.server.ServerConstants
import com.randomdroids.nytimes.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun okHttpProvider() =
        HttpLoggingInterceptor().run {
            level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
                val builder = chain.request().newBuilder()
                return@Interceptor chain.proceed(builder.build())
            }).build()
        }

    @Singleton
    @Provides
    fun retrofitProvider(okHttpClient: OkHttpClient): NYTimesServerService = Retrofit.Builder()
        .baseUrl(ServerConstants.URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run {
            create(NYTimesServerService::class.java)
        }

    @Provides
    fun nyTimesDataSourceProvider(nyTimesServerService: NYTimesServerService): RemoteDataSource = NYTimesServerDataSource(nyTimesServerService)

}