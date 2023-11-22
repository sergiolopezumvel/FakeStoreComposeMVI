package com.example.mviproject.data.remote

import com.example.mviproject.data.Product
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts():List<Product>
}