package com.example.mviproject.data

import com.example.mviproject.data.remote.ProductApi
import com.example.mviproject.domain.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * ProductDataSource
 *
 * @author (c) 2023, Umvel Inc.
 */
class ProductRepositoryImpl(private val productApi: ProductApi): ProductRepository {
    override fun getProducts(): Flow<List<Product>> = flow {
        emit(productApi.getProducts())
    }

    override fun getRecentProducts(amount: Int): Flow<List<Product>> = flow {
        emit(emptyList())
    }

    override suspend fun insertProduct(product: Product) {

    }

    override suspend fun deleteProduct(id: Long) {

    }
}