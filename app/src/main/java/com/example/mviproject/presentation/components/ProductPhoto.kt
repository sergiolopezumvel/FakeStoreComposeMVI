package com.example.mviproject.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mviproject.data.Product

/**
 * ProductPhoto
 *
 * @author (c) 2023, Umvel Inc.
 */
@Composable
fun ProductPhoto(
    product: Product?,
    modifier: Modifier = Modifier,
    imageSize:Dp = 25.dp
){
    val photoModifier = modifier.clip(RoundedCornerShape(35))
    val urlImage = product?.image

    if (urlImage!=null){
        AsyncImage(
            model = urlImage,
            contentDescription = "${product.description}",
            modifier = photoModifier,
            contentScale = ContentScale.Crop
        )
    }else{
        Box(
            modifier = photoModifier
                .background(MaterialTheme.colorScheme.secondaryContainer),
            contentAlignment = Alignment.Center
        ){
            Icon(
                imageVector = Icons.Rounded.ShoppingCart,
                contentDescription = product?.description,
                modifier = Modifier.size(imageSize),
                tint = MaterialTheme.colorScheme.secondaryContainer
            )
        }
    }
}