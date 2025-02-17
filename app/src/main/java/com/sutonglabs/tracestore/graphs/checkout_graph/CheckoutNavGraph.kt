package com.sutonglabs.tracestore.graphs.checkout_graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.sutonglabs.tracestore.graphs.Graph
import com.sutonglabs.tracestore.graphs.cart_graph.CartScreen
//import com.sutonglabs.tracestore.graphs.cart_graph.CheckoutScreenWithContext
import com.sutonglabs.tracestore.ui.cart_screen.CartScreen
import com.sutonglabs.tracestore.ui.checkout_screen.CheckoutScreen
import com.sutonglabs.tracestore.ui.edit_address.EditAddressScreen

@Composable
fun CheckoutScreenWithContext(navController: NavHostController) {
    val context = LocalContext.current
    CheckoutScreen(context = context, navController = navController)
}

@Composable
fun EditAddressScreenWithContext(navController: NavHostController) {
    val context = LocalContext.current
    EditAddressScreen(navController = navController, context = context)
}


fun NavGraphBuilder.checkoutNavGraph(navController: NavHostController) {
    navigation(
        startDestination = "checkout_screen",
        route = Graph.CHECKOUT
    ) {
        composable("checkout_screen") {
            CheckoutScreenWithContext(navController)
        }
        composable(CheckoutScreen.EditAddressScreen.route) {
            EditAddressScreenWithContext(navController = navController)
        }
    }

}