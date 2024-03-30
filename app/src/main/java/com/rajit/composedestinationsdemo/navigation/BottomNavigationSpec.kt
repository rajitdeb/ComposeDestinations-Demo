package com.rajit.composedestinationsdemo.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.rajit.composedestinationsdemo.screen.destinations.HomeScreenDestination
import com.rajit.composedestinationsdemo.screen.destinations.SearchScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomNavigationSpec(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    val label: String
) {

    HomeScreen(HomeScreenDestination, Icons.Default.Home, "Home"),
    SearchScreen(SearchScreenDestination, Icons.Default.Search, "Search")

}