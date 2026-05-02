package com.tourist.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tourist.app.navigation.Screen
import com.tourist.app.navigation.bottomNavItems
import com.tourist.app.ui.screens.*
import com.tourist.app.ui.theme.IntelligentTouristTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntelligentTouristTheme {
                TouristApp()
            }
        }
    }
}

@Composable
fun TouristApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavRoutes = bottomNavItems.map { it.route }
    val showBottomBar = bottomNavRoutes.any { currentRoute == it }

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 8.dp
                ) {
                    bottomNavItems.forEach { item ->
                        val isSelected = currentRoute == item.route
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.label
                                )
                            },
                            label = {
                                Text(
                                    text = item.label,
                                    fontSize = 10.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = if (showBottomBar) innerPadding.calculateBottomPadding() else 0.dp)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onPlaceClick = { placeId ->
                        navController.navigate(Screen.PlaceDetail.createRoute(placeId))
                    }
                )
            }
            composable(Screen.Explore.route) {
                ExploreScreen(
                    onPlaceClick = { placeId ->
                        navController.navigate(Screen.PlaceDetail.createRoute(placeId))
                    }
                )
            }
            composable(Screen.Planner.route) {
                PlannerScreen()
            }
            composable(Screen.VoyageAI.route) {
                VoyageAIScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(Screen.PlaceDetail.route) { backStackEntry ->
                val placeId = backStackEntry.arguments?.getString("placeId")?.toIntOrNull() ?: 1
                PlaceDetailScreen(
                    placeId = placeId,
                    onBack = { navController.popBackStack() },
                    onPlanTrip = {
                        navController.navigate(Screen.Planner.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}
