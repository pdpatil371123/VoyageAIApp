package com.tourist.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.SmartToy
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.icons.outlined.EventNote

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Planner : Screen("planner")
    object VoyageAI : Screen("voyage_ai")
    object Profile : Screen("profile")
    object PlaceDetail : Screen("place_detail/{placeId}") {
        fun createRoute(placeId: Int) = "place_detail/$placeId"
    }
}

data class BottomNavItem(
    val label: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem("Home", Screen.Home.route, Icons.Filled.Home, Icons.Outlined.Home),
    BottomNavItem("Explore", Screen.Explore.route, Icons.Filled.Explore, Icons.Outlined.Explore),
    BottomNavItem("Planner", Screen.Planner.route, Icons.Filled.EventNote, Icons.Outlined.EventNote),
    BottomNavItem("Voyage AI", Screen.VoyageAI.route, Icons.Filled.SmartToy, Icons.Outlined.SmartToy),
    BottomNavItem("Profile", Screen.Profile.route, Icons.Filled.Person, Icons.Outlined.Person),
)
