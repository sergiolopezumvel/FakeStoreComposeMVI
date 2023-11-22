package com.example.mviproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mviproject.domain.ProductRepository

class ProductViewModelFactory(private val productRepository: ProductRepository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductListViewModel::class.java)) {
            return ProductListViewModel(productRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}