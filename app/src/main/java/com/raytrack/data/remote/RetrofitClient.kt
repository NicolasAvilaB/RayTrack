package com.raytrack.data.remote

import com.raytrack.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object RetrofitClient {

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .header("User-Agent", "RayTrac/1.0")
                .header("Accept", "application/json")
                .build()

            chain.proceed(request)
        }
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }
        }
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://nominatim.openstreetmap.org/")
        .client(httpClient)
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .build()
}
