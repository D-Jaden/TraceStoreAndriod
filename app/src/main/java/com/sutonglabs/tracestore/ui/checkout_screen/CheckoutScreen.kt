package com.sutonglabs.tracestore.ui.checkout_screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sutonglabs.tracestore.api.request_models.CreateAddressRequest
import com.sutonglabs.tracestore.api.request_models.CreateOrderRequest
import com.sutonglabs.tracestore.api.request_models.Product
import com.sutonglabs.tracestore.models.CartResponse
import com.sutonglabs.tracestore.ui.login.LoginScreen
import com.sutonglabs.tracestore.viewmodels.AddressViewModel
import com.sutonglabs.tracestore.viewmodels.CartViewModel
import com.sutonglabs.tracestore.viewmodels.OrderViewModel

@Composable
fun CheckoutScreen(
    navController: NavController,
    addressViewModel: AddressViewModel = hiltViewModel(),
    orderViewModel: OrderViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
    context: Context
){
//    val orderViewModel: OrderViewModel = viewModel()
    val state = addressViewModel.state.value
    val orderState = orderViewModel.state.value
//    LaunchedEffect(orderRequest) {
//        orderViewModel.createAddress(context = context, orderRequest = orderRequest)
//    }

    val createOrderRequest = generateCreateOrderRequest(cartViewModel = cartViewModel, addressID = 1)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val address = state.address?.get(0)

        Log.d("CheckoutScreen TEST", "Address: $address")

        address?.let {
            AddressCard(
                id = it.id,
                name = it.name,
                phoneNumber = it.phoneNumber ?: "N/A",
                pincode = it.pincode ?: "N/A",
                city = it.city ?: "N/A",
                stateName = it.state ?: "N/A",
                locality = it.locality ?: "N/A",
                buildingName = it.buildingName ?: "N/A",
                landmark = it.landmark ?: "N/A",
                context = context
            )
        }

        // orderViewModel.createAddress(context=context, orderRequest = orderRequest)
        Button(onClick = {
            if (createOrderRequest != null) {
                Log.d("CheckoutScreen", "Order Request: $createOrderRequest")
                orderViewModel.createOrder(context = context, orderRequest = createOrderRequest)
            }

        }) {
            Text(text = "Place Order Now!")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                // Navigate to the EditAddressScreen passing the address ID.
                address?.let {
                    navController.navigate("edit_address_screen")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Edit Address")
        }

    }
}

@Composable
fun AddressCard(id: Int,
                name: String,
                phoneNumber: String,
                pincode: String,
                city: String,
                stateName: String,
                locality: String,
                buildingName: String,
                landmark: String,
                modifier: Modifier = Modifier,
                context: Context,
                ) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Phone: $phoneNumber",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Address: ${buildingName}, ${locality}, $city - $pincode",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "State: $stateName",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Landmark: $landmark",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


fun generateCreateOrderRequest(cartViewModel: CartViewModel,addressID: Int): CreateOrderRequest? {

    val products = cartViewModel.state.value.product?.map {
        Product(
            productID = it.productId.toString(),
            quantity = it.quantity.toString()
        )
    }

    val totalAmount = cartViewModel.state.value.product?.sumOf {
        it.product.price * it.quantity
    }.toString()

    return products?.let {
        CreateOrderRequest(
        products = it,
        totalAmount = totalAmount,
        addressID = addressID
    )
    }
}