package com.rajit.composedestinationsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rajit.composedestinationsdemo.navigation.BottomNavigationSpec
import com.rajit.composedestinationsdemo.screen.NavGraphs
import com.rajit.composedestinationsdemo.screen.appCurrentDestinationAsState
import com.rajit.composedestinationsdemo.screen.destinations.Destination
import com.rajit.composedestinationsdemo.screen.home.components.MyBottomSheet
import com.rajit.composedestinationsdemo.screen.startAppDestination
import com.rajit.composedestinationsdemo.ui.theme.ComposeDestinationsDemoTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.popUpTo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDestinationsDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyScaffold()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffold() {

    val navController = rememberNavController()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = { CustomTopAppBar(navController = navController) },
        bottomBar = { CustomBottomNavBar(navController = navController) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showBottomSheet = true }) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Hello World")
            }
        }
    ) { contentPadding ->
        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            sheetPeekHeight = 0.dp,
            sheetContent = {
                if (showBottomSheet) {
                    MyBottomSheet(
                        onSubmit = { showBottomSheet = false },
                        onReset = { showBottomSheet = false },
                        onDismiss = {
                            showBottomSheet = false
                        }
                    )
                }
            }
        ) {
            DestinationsNavHost(
                navController = navController,
                navGraph = NavGraphs.root,
                modifier = Modifier.padding(contentPadding)
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(navController: NavController) {
    val currentDestination: Destination =
        navController.appCurrentDestinationAsState().value
            ?: NavGraphs.root.startAppDestination

    TopAppBar(
        title = {
            when {
                currentDestination.route.contains("detail") -> Text(text = "Person Details")
                currentDestination.route.contains("search") -> Text(text = "Person Search")
                else -> Text(text = "Home")
            }
        },
        navigationIcon = {
            if (currentDestination.route.contains("detail")) {
                IconButton(
                    onClick = { navController.navigateUp() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Up Button"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.purple_500),
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White
        )
    )
}

@Composable
fun CustomBottomNavBar(
    navController: NavController
) {
    val currentDestination: Destination =
        navController.appCurrentDestinationAsState().value
            ?: NavGraphs.root.startAppDestination

    if (!currentDestination.route.contains("detail_screen")) {
        BottomAppBar {
            BottomNavigationSpec.entries.forEach { destination ->
                NavigationBarItem(
                    selected = currentDestination == destination.direction,
                    onClick = {

                        navController.navigate(destination.direction) {

                            popUpTo(NavGraphs.root) {
                                inclusive = false
                            }

                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(destination.icon, contentDescription = destination.label) },
                    label = { Text(destination.label) }
                )
            }
        }
    }
}
