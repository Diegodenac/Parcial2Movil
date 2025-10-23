package com.example.myapplication.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.myapplication.feature.dollar.presentation.DollarScreen
import com.example.myapplication.feature.github.presentation.github.GithubScreen
import com.example.myapplication.feature.login.presentation.login.LoginScreen
import com.example.myapplication.feature.loginRecuperatorio.presentation.LoginRecuperatorioScreen
import com.example.myapplication.feature.movie_pages.presentation.MoviesScreen
import com.example.myapplication.feature.profile.presentation.profile.ProfileScreen
import com.example.myapplication.feature.recoverpassRecuperatorio.presentation.RecoverPassScreen

@Composable
fun AppNavigation(navigationViewModel: NavigationViewModel, modifier: Modifier, navController: NavHostController) {
    LaunchedEffect(Unit) {
        navigationViewModel.navigationCommand.collect { command ->
            when (command) {
                is NavigationViewModel.NavigationCommand.NavigateTo -> {
                    navController.navigate(command.route) {
                        // Configuración del back stack según sea necesario
                        when (command.options) {
                            NavigationOptions.CLEAR_BACK_STACK -> {
                                popUpTo(0) // Limpiar todo el back stack
                            }

                            NavigationOptions.REPLACE_HOME -> {
                                popUpTo(Screen.DollarScreen.route) { inclusive = true }
                            }

                            else -> {
                                // Navegación normal
                            }
                        }
                    }
                }

                is NavigationViewModel.NavigationCommand.PopBackStack -> {
                    navController.popBackStack()
                }
            }
        }
    }


    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route,
        modifier = modifier
    ) {
        composable(Screen.DollarScreen.route) {
            DollarScreen()
        }

        composable(Screen.GithubScreen.route) {
            GithubScreen(modifier = Modifier.padding())
        }

        composable(Screen.MovieScreen.route){
            MoviesScreen(
                modifier = Modifier.padding()
            )
        }

        composable(Screen.ProfileScreen.route){
            ProfileScreen(
                modifier = Modifier.padding()
            )
        }

        composable(Screen.LoginScreen.route){
            LoginScreen(
                onSuccess = {
                    navController.navigate(Screen.GithubScreen.route)
                }
            )
        }
    }
}