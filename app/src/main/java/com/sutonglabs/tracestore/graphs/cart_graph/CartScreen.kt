package com.sutonglabs.tracestore.graphs.cart_graph

sealed class CartScreen(val route: String) {
    data object CheckoutScreen : CartScreen("checkout_screen")
}