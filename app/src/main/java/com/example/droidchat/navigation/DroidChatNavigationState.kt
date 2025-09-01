package com.example.droidchat.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.droidchat.ui.feature.chats.navigateToChats

@Composable
fun rememberDroidChatNavigationState(
    navController: NavHostController = rememberNavController()
): DroidChatNavigationState {
    return remember(navController) {
        DroidChatNavigationState(navController)
    }
}

@Stable
class DroidChatNavigationState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable
        get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable
        get() = TopLevelDestination.entries.firstOrNull { topLevelDestination ->
            currentDestination?.hasRoute(topLevelDestination.route) == true
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (topLevelDestinations) {
            Route.ChatsRoute -> navController.navigateToChats(topLevelNavOptions)
            Route.UserRoute -> {
                //TODO IMPLEMENTAR
            }

            Route.ProfileRoute -> {
                //TODO IMPLEMENTAR
            }
        }
    }
}