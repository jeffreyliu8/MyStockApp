package com.example.mystockapp.hilt

import com.example.mystockapp.BuildConfig
import com.example.mystockapp.repository.WebRepository
import com.example.mystockapp.repository.data.WebApi
import com.example.mystockapp.repository.impl.WebRepositoryImpl
import com.example.mystockapp.ui.SnackBarManager
import com.example.mystockapp.usecase.GetStocksFromWeb
import com.example.mystockapp.usecase.MainActivityUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object AppModule {
    @Provides
    @ViewModelScoped
    fun provideMainActivityUseCases(
        webRepository: WebRepository,
    ): MainActivityUseCases {
        return MainActivityUseCases(
            getStocksFromWeb = GetStocksFromWeb(webRepository),
        )
    }
}


@Module
@InstallIn(SingletonComponent::class)
object AppModuleForSingleton {
    @Provides
    @Singleton
    fun provideWebService(): WebApi {
        val httpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            // set your desired log level
            // add your other interceptors …
            // add logging as last interceptor
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)  // <-- this is the important line!
        }

        return Retrofit.Builder()
            .baseUrl("https://storage.googleapis.com/cash-homework/cash-stocks-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(WebApi::class.java)
    }


    @Provides
    @Singleton
    fun providesWebRepository(): WebRepository {
        return WebRepositoryImpl(
            webApi = provideWebService()
        )
    }

    @Singleton
    @Provides
    fun provideSnackBarMgr(): SnackBarManager {
        return SnackBarManager
    }
}