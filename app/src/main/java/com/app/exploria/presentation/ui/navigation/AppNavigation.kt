package com.app.exploria.presentation.ui.navigation

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.exploria.presentation.ui.features.detail.composables.DetailScreen
import com.app.exploria.presentation.ui.features.favorite.composables.FavoriteScreen
import com.app.exploria.presentation.ui.features.home.composables.HomeScreen
import com.app.exploria.presentation.ui.features.planning.composables.FinalPlanningScreen
import com.app.exploria.presentation.ui.features.planning.composables.PlanningScreen
import com.app.exploria.presentation.ui.features.planning.composables.SecondPlanningScreen
import com.app.exploria.presentation.ui.features.planning.composables.SelectDestinationScreen
import com.app.exploria.presentation.ui.features.profile.composables.ProfileScreen
import com.app.exploria.presentation.ui.features.register.composables.RegisterScreen
import com.app.exploria.presentation.ui.features.survey.composables.SurveyScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Plan.route) { PlanningScreen(navController) }
        composable(Screen.Favorite.route) { FavoriteScreen(navController) }
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Register.route) { RegisterScreen(navController) }
        composable(Screen.Survey.route) { SurveyScreen(navController) }
        composable(Screen.Profile.route) { ProfileScreen(navController) }
        composable(Screen.SecondPlan.route) { SecondPlanningScreen(navController) }
        composable(Screen.FinalPlan.route) { FinalPlanningScreen(navController) }
        composable(Screen.SelectDestination.route) { SelectDestinationScreen(navController) }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType})
        ) { navBackStackEntry ->
            val detailId = navBackStackEntry.arguments?.getString("id")
            DetailScreen(detailId)
        }
    }
}
