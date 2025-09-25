package com.example.myapplication.navigation

sealed class Screen(val route: String) {
    object LoginScreen: Screen("login")
    object GithubScreen: Screen("github")
    object ProfileScreen: Screen("profile")
    object LoginRecuperatorioScreen: Screen("login-recuperatorio")
    object RecoverPasswordScreen: Screen("recover-password")

    object MovieScreen: Screen("movies")
    object DollarScreen: Screen("dollar")
}