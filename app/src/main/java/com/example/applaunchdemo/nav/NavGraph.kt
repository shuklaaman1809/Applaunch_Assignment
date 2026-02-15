package com.example.applaunchdemo.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applaunchdemo.ui.screens.UserDetailScreen
import com.example.applaunchdemo.ui.screens.UserListScreen
import com.example.applaunchdemo.ui.vm.UserDetailViewModel
import com.example.applaunchdemo.ui.vm.UserListViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
fun NavGraph() {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = "list") {

        composable("list") {
            val vm: UserListViewModel = hiltViewModel()

            UserListScreen(vm) { user ->
                nav.navigate("details/${user.id}")
            }
        }

        composable(
            route = "details/{userId}",
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->

            val userId = backStackEntry.arguments?.getInt("userId")
                ?: return@composable

            val vm: UserDetailViewModel = hiltViewModel()

            UserDetailScreen(
                viewModel = vm,
                userId = userId,
                onBack = { nav.popBackStack() }
            )
        }
    }
}