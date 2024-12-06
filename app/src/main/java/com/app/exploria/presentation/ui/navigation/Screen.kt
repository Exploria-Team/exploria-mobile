package com.app.exploria.presentation.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Login : Screen("login")
    object Register : Screen("register")
    object Profile : Screen("profile")
    object Survey : Screen("survey")
    object Plan : Screen("plan")
    object SecondPlan : Screen("second_plan")
    object FinalPlan : Screen("final_plan")
    object SelectDestination : Screen("select_destination")
    object Detail : Screen("detail/{id}")
    object DetailGuide : Screen("detailguide/{id}")
    object Search : Screen("search")
    object Guide : Screen("guide")
    object Splash : Screen("splash")
}