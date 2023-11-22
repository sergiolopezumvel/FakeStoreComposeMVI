package com.example.mviproject.domain

import com.example.mviproject.data.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<List<Product>>
    fun getRecentProducts(amount: Int): Flow<List<Product>>
    suspend fun insertProduct(product: Product)
    suspend fun deleteProduct(id: Long)
}
