package com.sutonglabs.tracestore.ui.dashboard_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.sutonglabs.tracestore.models.Product
import com.sutonglabs.tracestore.viewmodels.DashboardViewModel

@Composable
fun DashboardScreen(
    popularProductState: LazyListState = rememberLazyListState(),
    recommendedProductState: LazyListState = rememberLazyListState(),
    productViewModel: DashboardViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit,

){
    val state = productViewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .heightIn(max = 2048.dp)
    ) {
        Text(
            text = "Popular Products",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        LazyRow(state = popularProductState) {
            state.product?.let { products ->
                items(products) { product ->
                    ProductCard(product, onItemClick)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Recommended for You",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        LazyRow(state = recommendedProductState) {
            state.product?.let { products ->
                items(products) { product ->
                    ProductCard(product, onItemClick)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "All Products",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        LazyColumn(state = rememberLazyListState()) {
            state.product?.let { products ->
                items(products.chunked(2)) { productPair ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        productPair.forEach { product ->
                            ProductCard(product, onItemClick)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, onItemClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemClick(product.id) }
            .width(150.dp)
            .height(200.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Image(
                painter = rememberImagePainter("https://upload.wikimedia.org/wikipedia/commons/1/15/Cat_August_2010-4.jpg"), // product.image
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = "${product.price} INR",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}