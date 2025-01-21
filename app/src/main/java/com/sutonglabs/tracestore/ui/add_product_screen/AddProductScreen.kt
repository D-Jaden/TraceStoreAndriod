package com.sutonglabs.tracestore.ui.add_product_screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sutonglabs.tracestore.models.Product
import com.sutonglabs.tracestore.viewmodels.AddProductViewModel
import com.sutonglabs.tracestore.viewmodels.AddProductViewModelFactory
import com.sutonglabs.tracestore.repository.ProductRepository

@Composable
fun AddProductScreen(
    navHostController: NavHostController,
    productRepository: ProductRepository,
    addProductViewModel: AddProductViewModel = viewModel(factory = AddProductViewModelFactory(productRepository))
) {
    // States to hold user inputs
    var productName by remember { mutableStateOf(TextFieldValue()) }
    var productDescription by remember { mutableStateOf(TextFieldValue()) }
    var productPrice by remember { mutableStateOf(TextFieldValue()) }

    val addProductStatus = addProductViewModel.addProductStatus.value

    // Get Local context to show toast messages
    val context = LocalContext.current

    // Handle the result of adding a product
    LaunchedEffect(addProductStatus) {
        if (addProductStatus != null) {
            if (addProductStatus) {
                // Show success message
                Toast.makeText(context, "Product added successfully!", Toast.LENGTH_SHORT).show()
            } else {
                // Show error message
                Toast.makeText(context, "Failed to add product", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = productName,
            onValueChange = { productName = it },
            label = { Text("Product Name") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = productDescription,
            onValueChange = { productDescription = it },
            label = { Text("Product Description") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = productPrice,
            onValueChange = { productPrice = it },
            label = { Text("Product Price") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                val product = Product(
                    name = productName.text,
                    description = productDescription.text,
                    price = productPrice.text.toIntOrNull() ?: 0 // Handle invalid input gracefully
                )
                addProductViewModel.addProduct(product)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Product")
        }
    }
}

