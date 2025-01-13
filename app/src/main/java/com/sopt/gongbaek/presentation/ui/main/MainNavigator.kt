package com.sopt.gongbaek.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.sopt.gongbaek.presentation.model.Route
import com.sopt.gongbaek.presentation.type.MainBottomNavBarTabType
import com.sopt.gongbaek.presentation.ui.grouplist.navigation.navigateGroupList
import com.sopt.gongbaek.presentation.ui.home.navigation.navigateHome
import com.sopt.gongbaek.presentation.ui.mygroup.navigation.navigateMyGroup

class MainNavigator(
    val navController: NavHostController
) {
    val startDestination = MainBottomNavBarTabType.HOME.route

    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentMainBottomNavBarTab: MainBottomNavBarTabType?
        @Composable get() = MainBottomNavBarTabType.find { mainBottomNavigationTabRoute ->
            currentDestination?.route == mainBottomNavigationTabRoute::class.simpleName
        }

    fun navigate(mainBottomNavBarTabType: MainBottomNavBarTabType) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        when (mainBottomNavBarTabType) {
            MainBottomNavBarTabType.GROUP_LIST -> navController.navigateGroupList(navOptions)

            MainBottomNavBarTabType.MY_GROUP -> navController.navigateMyGroup(navOptions)

            MainBottomNavBarTabType.HOME -> navController.navigateHome(navOptions)

            MainBottomNavBarTabType.TIMETABLE -> {}

            MainBottomNavBarTabType.MY_PAGE -> {}
        }
    }

    private fun popBackStack() {
        navController.popBackStack()
    }

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean =
        navController.currentDestination?.route == T::class.simpleName

    @Composable
    fun showBottomBar(): Boolean = MainBottomNavBarTabType.contains {
        currentDestination?.route == it::class.simpleName
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
): MainNavigator = remember(navController) {
    MainNavigator(navController = navController)
}
