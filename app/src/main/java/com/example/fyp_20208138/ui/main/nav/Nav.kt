package com.example.fyp_20208138.ui.main.nav

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fyp_20208138.ui.main.gallery.Gallery
import com.example.fyp_20208138.ui.main.home.Home
import com.example.fyp_20208138.ui.main.userProfile.UserProfile
import kotlinx.coroutines.launch

//import com.example.fyp_20208138.ui.userProfile.UserProfile
//import com.google.firebase.auth.FirebaseUser


@ExperimentalMaterialApi
@Composable
fun Nav() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
//        scaffoldState = scaffoldState,
//        drawerContent = {
//            // Drawer content
//        },



//        floatingActionButton = {
//            FloatingActionButton(onClick = { /* ... */ }) {
//                "+"
//            }
//        },
//        // Defaults to false
//        isFloatingActionButtonDocked = true,
//        floatingActionButtonPosition = FabPosition.Center,

        bottomBar = {

            BottomAppBar(

                cutoutShape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),


        ) {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination?.hierarchy

            NavItem.data.forEach { navItem ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = navItem.icon),
                            contentDescription = navItem.title
                        )
                    },
                    label = { Text(navItem.title) },
                    selected = currentDestination?.any { it.route == navItem.route } == true,
                    onClick = {
                        navController.navigate(navItem.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = false
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = false
                        }
                    }
                )
            }

//                ExtendedFloatingActionButton(
//                    text = { Text("Profile") },
//                    onClick = {
//                        scope.launch {
//                            scaffoldState.drawerState.apply {
//                                if (isClosed) open() else close()
//                            }
//                        }
//                    }
//                )

        }

    }) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "gallery",
        ) {
            composable("home") { Home() }
            composable("gallery") { Gallery() }
            composable("profile") { UserProfile() }

        }
    }
}


data class NavItem(
    val route: String,
    val icon: Int,
    val title: String,
//    val compose:@Composable () -> Unit
) {
    companion object {
        val data = listOf(
            NavItem("home", android.R.drawable.btn_star_big_off, "Home"),
            NavItem("gallery", android.R.drawable.btn_star_big_off, "Gallery"),
//            NavItem("profile", android.R.drawable.btn_star_big_off, "Profile"),

            )
    }
}



