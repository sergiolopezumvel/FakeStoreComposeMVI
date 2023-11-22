package com.example.mviproject.presentation

import com.example.mviproject.data.Product

sealed interface ProductListEvent{
    object OnAddNewProductClick: ProductListEvent
    object DismissProduct: ProductListEvent
    data class SelectProduct(val product: Product): ProductListEvent
}