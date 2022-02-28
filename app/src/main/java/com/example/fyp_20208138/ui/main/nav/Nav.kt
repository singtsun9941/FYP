package com.example.fyp_20208138.ui.main.nav

import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fyp_20208138.ui.main.home.Home
import com.example.fyp_20208138.ui.main.userProfile.UserProfile

//import com.example.fyp_20208138.ui.userProfile.UserProfile
//import com.google.firebase.auth.FirebaseUser


@ExperimentalMaterialApi
@Composable
fun Nav() {
    val navController = rememberNavController()

    Scaffold(bottomBar = {

        BottomNavigation(modifier = Modifier.height(64.dp)) {

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

        }

    }) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
        ) {
            composable("home") { Home() }
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
            NavItem("profile", android.R.drawable.btn_star_big_off, "Profile"),

            )
    }
}



