package com.example.mviproject.presentation

import com.example.mviproject.data.Product

data class ProductListState(
    val products: List<Product> = emptyList(),
    val recentlyAddedProducts: List<Product> = emptyList(),
    val selectedProduct: Product? = null,
    val isAddProductSheetOpen: Boolean = false,
    val isSelectedProductSheetOpen: Boolean = false
)
