package com.example.mviproject.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mviproject.data.Product
import com.example.mviproject.domain.ProductRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ProductListViewModel
 *
 * @author (c) 2023, Umvel Inc.
 */
class ProductListViewModel(
    private val productDataSource: ProductRepository
):ViewModel() {
    private val _state = MutableStateFlow(ProductListState())

    // it will take multiple flows
    val state = combine(
        _state,
        productDataSource.getProducts(),
        productDataSource.getRecentProducts(10),
    ) { state, products, recentProducts ->
        // and this block will now called
        // whenever any of these flows emits a new value and
        // it will provide us with all the most up-to-date values here
        state.copy(
            products = products,
            recentlyAddedProducts = recentProducts
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), ProductListState())

    var newProduct: Product? by mutableStateOf(null)
        private set

    fun onEvent(event: ProductListEvent) {
        when(event) {
            ProductListEvent.DismissProduct -> {
                viewModelScope.launch {
                    _state.update { it.copy(
                        isSelectedProductSheetOpen = false,
                        isAddProductSheetOpen = false,
                    ) }
                    delay(300L) // Animation delay
                    newProduct = null
                    _state.update { it.copy(
                        selectedProduct = null
                    ) }
                }
            }
            ProductListEvent.OnAddNewProductClick -> {
                _state.update { it.copy(
                    isAddProductSheetOpen = true
                ) }
                newProduct = Product(
                    id = null,
                    category = "",
                    description = "",
                    image = "",
                    price = 0.0,
                    rating = null,
                    title = ""
                )
            }
            is ProductListEvent.SelectProduct -> {
                _state.update { it.copy(
                    selectedProduct = event.product,
                    isSelectedProductSheetOpen = true
                ) }
            }
        }
    }
}
