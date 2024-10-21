package com.sutonglabs.tracestore.ui.home_screen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.sutonglabs.tracestore.graphs.home_graph.ShopHomeScreen

sealed class BottomNavItem(val tittle: String, val icon: ImageVector, val route: String) {
    object ProfileNav : BottomNavItem(
        tittle = "Profile",
        icon = Icons.Filled.Person,
        route = ShopHomeScreen.ProfileScreen.route
    )

    object FavouriteNav : BottomNavItem(
        tittle = "Favourite",
        icon = Icons.Filled.Favorite,
        route = ShopHomeScreen.FavouriteScreen.route
    )

    object ChatNav : BottomNavItem(
        tittle = "Chat",
        icon = Icons.Filled.ChatBubble,
        route = ShopHomeScreen.ConversationScreen.route
    )

    object HomeNav : BottomNavItem(
        tittle = "Home",
        icon = Icons.Filled.Home,
        route = ShopHomeScreen.DashboardScreen.route
    )

}
