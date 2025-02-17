package com.sutonglabs.tracestore.graphs.checkout_graph

sealed class CheckoutScreen(val route: String) {
    data object EditAddressScreen : CheckoutScreen("edit_address_screen")
}