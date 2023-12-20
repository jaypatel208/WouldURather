package dev.jay.wouldurather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.jay.wouldurather.BuildConfig
import dev.jay.wouldurather.data.api.DilemmaAPI
import dev.jay.wouldurather.data.datasource.DilemmaDataSource
import dev.jay.wouldurather.data.datasource.DilemmaDataSourceImpl
import dev.jay.wouldurather.ui.repository.DilemmaRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        val httpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoggingInterceptor)
        }
        httpClient.apply {
            readTimeout(60, TimeUnit.SECONDS)
        }
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_KEY).client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesDilemmaAPI(retrofit: Retrofit): DilemmaAPI {
        return retrofit.create(DilemmaAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesDilemmaDataSource(dilemmaAPI: DilemmaAPI): DilemmaDataSource {
        return DilemmaDataSourceImpl(dilemmaAPI)
    }

    @Singleton
    @Provides
    fun providesDilemmaRepository(dilemmaDataSource: DilemmaDataSource): DilemmaRepository {
        return DilemmaRepository(dilemmaDataSource)
    }
}