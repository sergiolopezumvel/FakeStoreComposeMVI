package com.example.mviproject.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mviproject.di.AppModule
import com.example.mviproject.presentation.ProductListScreen
import com.example.mviproject.presentation.ProductListViewModel
import com.example.mviproject.presentation.ProductViewModelFactory
import com.example.mviproject.ui.theme.ProductTheme

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    appModule: AppModule
) {
    ProductTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        val viewModel:ProductListViewModel = viewModel(
            factory = ProductViewModelFactory(appModule.productRepository)
        )

        val state by viewModel.state.collectAsState()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ProductListScreen(
                state = state,
                newProduct = viewModel.newProduct,
                onEvent = viewModel::onEvent
            )
        }
    }
}