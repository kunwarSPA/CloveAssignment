package com.clover.common.di

import com.clover.data.api.Api
import com.clover.data.repositoryImpl.CloveRepositoryImpl
import com.clover.data.useCaseImpl.CloveUseCaseImpl
import com.clover.domain.clovelist.repository.CloveRepository
import com.clover.domain.clovelist.usecase.CloveUseCase
import com.employee.data.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    private val baseUrl = "https://rickandmortyapi.com"

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun providesRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

    }


    @Singleton
    @Provides
    fun providesAPI(retrofit: Retrofit.Builder): Api {
        return retrofit.build().create(Api::class.java)
    }





    @Singleton
    @Provides
    fun provideCloveDetailsUseCase(cloveRepository: CloveRepository) : CloveUseCase {
        return  CloveUseCaseImpl(cloveRepository)
    }



    @Provides
    @Singleton
    fun providesLoggingInterceptor(): OkHttpClient {
        val httpInterceptor = HttpLoggingInterceptor()
        httpInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(httpInterceptor)
            .build()
    }




    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        val httpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(httpLoggingInterceptor)
        }
        httpClient.retryOnConnectionFailure(true)
        return httpClient

    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)



    @Provides
    @Singleton
    fun provideCloveRepository(api: Api): CloveRepository {
        return CloveRepositoryImpl(api )
    }







}