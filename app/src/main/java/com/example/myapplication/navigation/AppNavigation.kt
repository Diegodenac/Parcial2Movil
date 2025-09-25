package com.example.myapplication.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.myapplication.feature.dollar.presentation.DollarScreen
import com.example.myapplication.feature.github.presentation.github.GithubScreen
import com.example.myapplication.feature.login.presentation.login.LoginScreen
import com.example.myapplication.feature.loginRecuperatorio.presentation.LoginRecuperatorioScreen
import com.example.myapplication.feature.movie_pages.presentation.MoviesScreen
import com.example.myapplication.feature.profile.presentation.profile.ProfileScreen
import com.example.myapplication.feature.recoverpassRecuperatorio.presentation.RecoverPassScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route,
        /*enterTransition = { EnterTransition.None},
        exitTransition = { ExitTransition.None},*/
    ){
        composable(Screen.LoginScreen.route){
            LoginScreen(
                onSuccess = {
                    navController.navigate(Screen.DollarScreen.route)
                }
            )
        }
        composable(Screen.GithubScreen.route){
            Scaffold(modifier = Modifier.fillMaxSize()) {
                    innerPadding ->
                GithubScreen(modifier = Modifier.padding(innerPadding))
            }
        }
        composable(Screen.ProfileScreen.route) {
            Scaffold(modifier = Modifier.fillMaxSize()) {
                    innerPadding ->
                ProfileScreen(modifier = Modifier.padding(innerPadding))
            }
        }
        composable(Screen.LoginRecuperatorioScreen.route) {
            LoginRecuperatorioScreen(
                onSuccess = {
                    navController.navigate(Screen.RecoverPasswordScreen.route)
                }
            )
        }
        composable(Screen.RecoverPasswordScreen.route) {
            Scaffold(modifier = Modifier.fillMaxSize()) {
                    innerPadding ->
                RecoverPassScreen(modifier = Modifier.padding(innerPadding))
            }
        }
        composable(Screen.MovieScreen.route) {
            Scaffold(modifier = Modifier.fillMaxSize()) {
                    innerPadding ->
                MoviesScreen(modifier = Modifier.padding(innerPadding))
            }
        }
        composable(Screen.DollarScreen.route) {
            DollarScreen()
        }
    }
}