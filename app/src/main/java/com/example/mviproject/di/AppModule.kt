package com.example.mviproject.di

import com.example.mviproject.data.ProductRepositoryImpl
import com.example.mviproject.data.remote.ProductApi
import com.example.mviproject.domain.ProductRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * di
 *
 * @author (c) 2023, Umvel Inc.
 */
object AppModule {
    val productRepository: ProductRepository by lazy {
        ProductRepositoryImpl(productApi)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
    private val productApi = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(ProductApi::class.java)

}