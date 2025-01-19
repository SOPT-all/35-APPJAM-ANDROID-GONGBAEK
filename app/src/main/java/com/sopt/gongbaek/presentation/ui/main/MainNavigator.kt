package com.sopt.gongbaek.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.sopt.gongbaek.presentation.model.NavigationRoute
import com.sopt.gongbaek.presentation.type.MainBottomNavBarTabType
import com.sopt.gongbaek.presentation.ui.grouplist.navigation.navigateGroupListNavGraph
import com.sopt.gongbaek.presentation.ui.home.navigation.navigateHomeNavGraph
import com.sopt.gongbaek.presentation.ui.mygroup.navigation.navigateMyGroupNavGraph

class MainNavigator(
    val navController: NavHostController
) {
    val startDestination = NavigationRoute.SplashRoute.SPLASH

    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentMainBottomNavBarTab: MainBottomNavBarTabType?
        @Composable get() = currentDestination
            ?.route
            ?.let(MainBottomNavBarTabType.Companion::find)

    fun navigate(mainBottomNavBarTabType: MainBottomNavBarTabType) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        when (mainBottomNavBarTabType) {
            MainBottomNavBarTabType.GROUP_LIST -> navController.navigateGroupListNavGraph(navOptions)

            MainBottomNavBarTabType.MY_GROUP -> navController.navigateMyGroupNavGraph(navOptions)

            MainBottomNavBarTabType.HOME -> navController.navigateHomeNavGraph(navOptions)

            MainBottomNavBarTabType.TIMETABLE -> {}

            MainBottomNavBarTabType.MY_PAGE -> {}
        }
    }

    @Composable
    fun showBottomBar(): Boolean {
        val currentRoute = currentDestination?.route ?: return false
        val bottomBarRoutes = listOf(
            NavigationRoute.MainBottomNavBarTabRoute.GROUP_LIST_TAB,
            NavigationRoute.MainBottomNavBarTabRoute.MY_GROUP_TAB,
            NavigationRoute.MainBottomNavBarTabRoute.HOME_TAB,
            NavigationRoute.MainBottomNavBarTabRoute.TIMETABLE_TAB,
            NavigationRoute.MainBottomNavBarTabRoute.MY_PAGE_TAB
        )
        return bottomBarRoutes.contains(currentRoute + "_route")
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
): MainNavigator = remember(navController) {
    MainNavigator(navController = navController)
}
