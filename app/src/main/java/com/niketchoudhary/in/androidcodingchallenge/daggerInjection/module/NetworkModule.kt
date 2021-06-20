package com.niketchoudhary.`in`.androidcodingchallenge.daggerInjection.module


import android.util.Log
import com.google.gson.Gson
import com.niketchoudhary.`in`.androidcodingchallenge.BuildConfig
import com.niketchoudhary.`in`.androidcodingchallenge.utility.*
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    private var TEST_SERVER_URL: String = BuildConfig.testServerUrl

    @Provides
    fun provideExecutor() = AppExecutors()

    @Provides
    fun webServiceHolder() = WebServiceHolder.instance

    @Provides
    @Singleton
    fun provideGson() = Gson()


    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(150, TimeUnit.SECONDS)
        .readTimeout(150, TimeUnit.SECONDS)
        .writeTimeout(150, TimeUnit.SECONDS)

        .addInterceptor { chain ->
            val original = chain.request()
            Log.e(
                "NetworkModule",
                "line : ${Thread.currentThread().stackTrace[2].lineNumber} -> api called - ${
                    chain.request().url()
                } ${chain.request().body().toString()}"
            )

            val request = original.newBuilder()
                .build()

            chain.proceed(request)
        }.build()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(AppConfig.TEST_SERVER_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideApiWebservice(restAdapter: Retrofit): WebService {
        val webService = restAdapter.create(WebService::class.java)
        WebServiceHolder.instance.setAPIService(webService)
        return webService
    }
}