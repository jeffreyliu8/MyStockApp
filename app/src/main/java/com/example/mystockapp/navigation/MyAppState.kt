package com.example.mystockapp.navigation

import android.content.res.Resources
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.mystockapp.ui.SnackBarManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Remembers and creates an instance of [MyAppState]
 */
@Composable
fun rememberMyAppState(
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    navController: NavHostController,
    snackBarManager: SnackBarManager = SnackBarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(snackBarHostState, navController, snackBarManager, resources, coroutineScope) {
    MyAppState(snackBarHostState, navController, snackBarManager, resources, coroutineScope)
}

/**
 * Responsible for holding state related to [MyStockApp] and containing UI-related logic.
 */
@Stable
class MyAppState(
    val snackBarHostState: SnackbarHostState,
    private val navController: NavHostController,
    private val snackBarManager: SnackBarManager,
    private val resources: Resources,
    coroutineScope: CoroutineScope
) {
    // Process snackbars coming from SnackbarManager
    init {
        coroutineScope.launch {
            snackBarManager.messages.collect { currentMessages ->
                if (currentMessages.isNotEmpty()) {
                    val message = currentMessages[0]
                    val text = resources.getString(message.messageId, message.extra)

                    // Display the snackbar on the screen. `showSnackbar` is a function
                    // that suspends until the snackbar disappears from the screen
                    snackBarHostState.showSnackbar(text)
                    // Once the snackbar is gone or dismissed, notify the SnackbarManager
                    snackBarManager.setMessageShown(message.id)
                }
            }
        }
    }

    // ----------------------------------------------------------
    // BottomBar state source of truth
    // ----------------------------------------------------------

//    val bottomBarTabs = HomeSections.values()
//    private val bottomBarRoutes = bottomBarTabs.map { it.route }

    // Reading this attribute will cause recompositions when the bottom bar needs shown, or not.
    // Not all routes need to show the bottom bar.
//    val shouldShowBottomBar: Boolean
//        @Composable get() = navController
//            .currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes

    // ----------------------------------------------------------
    // Navigation state source of truth
    // ----------------------------------------------------------

//    private val currentRoute: String?
//        get() = navController.currentDestination?.route

    fun upPress() {
        navController.navigateUp()
    }

//    fun navigateToBottomBarRoute(route: String) {
//        if (route != currentRoute) {
//            navController.navigate(route) {
//                launchSingleTop = true
//                restoreState = true
//                // Pop up backstack to the first destination and save state. This makes going back
//                // to the start destination when pressing back in any other bottom tab.
//                popUpTo(findStartDestination(navController.graph).id) {
//                    saveState = true
//                }
//            }
//        }
//    }

    fun navigateToDetail(stockId: String, from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.DetailScreen.passStockId(stockId))
        }
    }


}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

//private val NavGraph.startDestination: NavDestination?
//    get() = findNode(startDestinationId)

/**
 * Copied from similar function in NavigationUI.kt
 *
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:navigation/navigation-ui/src/main/java/androidx/navigation/ui/NavigationUI.kt
 */
//private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
//    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
//}

/**
 * A composable function that returns the [Resources]. It will be recomposed when `Configuration`
 * gets updated.
 */
@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}
