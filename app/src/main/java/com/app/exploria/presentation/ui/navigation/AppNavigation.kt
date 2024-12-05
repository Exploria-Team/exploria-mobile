package com.app.exploria.presentation.ui.navigation

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.exploria.presentation.ui.features.detail.composables.DetailScreen
import com.app.exploria.presentation.ui.features.favorite.composables.FavoriteScreen
import com.app.exploria.presentation.ui.features.guider.composables.GuideListScreen
import com.app.exploria.presentation.ui.features.guider.composables.GuiderDetailScreen
import com.app.exploria.presentation.ui.features.home.composables.HomeScreen
import com.app.exploria.presentation.ui.features.planning.composables.FinalPlanningScreen
import com.app.exploria.presentation.ui.features.planning.composables.PlanningScreen
import com.app.exploria.presentation.ui.features.planning.composables.SecondPlanningScreen
import com.app.exploria.presentation.ui.features.planning.composables.SelectDestinationScreen
import com.app.exploria.presentation.ui.features.profile.composables.ProfileScreen
import com.app.exploria.presentation.ui.features.register.composables.RegisterScreen
import com.app.exploria.presentation.ui.features.search.composables.SearchScreen
import com.app.exploria.presentation.ui.features.survey.composables.SurveyScreen
import com.app.exploria.presentation.viewModel.MainViewModel

@Composable
fun AppNavigation(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    val userState by mainViewModel.userModel.collectAsState()

    LaunchedEffect(Unit) {
        mainViewModel.loadUser()
    }

    LaunchedEffect(userState) {
        if (userState?.isLogin == true) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
        } else {
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = if (userState?.isLogin == true) Screen.Home.route else Screen.Login.route,
    ) {

        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Plan.route) { PlanningScreen(navController) }
        composable(Screen.Favorite.route) { FavoriteScreen(navController) }
        composable(Screen.Login.route) { LoginScreen(navController, mainViewModel) }
        composable(Screen.Register.route) { RegisterScreen(navController, mainViewModel) }
        composable(Screen.Survey.route) { SurveyScreen(navController) }
        composable(Screen.Profile.route) { ProfileScreen(navController, mainViewModel) }
        composable(Screen.SecondPlan.route) { SecondPlanningScreen(navController) }
        composable(Screen.FinalPlan.route) { FinalPlanningScreen(navController) }
        composable(Screen.SelectDestination.route) { SelectDestinationScreen(navController) }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val detailId = navBackStackEntry.arguments?.getString("id")
            DetailScreen(detailId, navController)
        }
        composable(
            route = Screen.DetailGuide.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val detailId = navBackStackEntry.arguments?.getString("id")
            GuiderDetailScreen(detailId, navController)
        }
        composable(Screen.Search.route) { SearchScreen(navController) }
        composable(Screen.Guide.route) { GuideListScreen(navController) }
    }
}
