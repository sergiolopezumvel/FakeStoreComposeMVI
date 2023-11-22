package com.example.mviproject.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mviproject.data.Product
import com.example.mviproject.presentation.components.ProductDetailSheet
import com.example.mviproject.presentation.components.ProductListItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    state: ProductListState,
    newProduct: Product?,
    onEvent: (ProductListEvent) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(ProductListEvent.OnAddNewProductClick)
                },
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add product"
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "My products (${state.products.size})",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            items(state.products) { product ->
                ProductListItem(
                    product = product,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onEvent(ProductListEvent.SelectProduct(product))
                        }
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }

    ProductDetailSheet(
        isOpen = state.isSelectedProductSheetOpen,
        selectedProduct = state.selectedProduct,
        onEvent = onEvent,
    )
    /*AddProductSheet(
        state = state,
        newProduct = newProduct,
        isOpen = state.isAddProductSheetOpen,
        onEvent = { event ->
            if(event is ProductListEvent.OnAddPhotoClicked) {
                imagePicker.pickImage()
            }
            onEvent(event)
        },
    )*/
}