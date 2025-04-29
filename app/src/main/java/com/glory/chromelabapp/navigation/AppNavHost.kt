package com.glory.chromelabapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.glory.chromelabapp.screens.about.AboutScreen
import com.glory.chromelabapp.screens.home.HomeScreen
import com.glory.chromelabapp.ui.theme.screens.login.LoginScreen
import com.glory.chromelabapp.ui.theme.screens.property.AddPropertyScreen
import com.glory.chromelabapp.ui.theme.screens.property.EditPropertyScreen
import com.glory.chromelabapp.ui.theme.screens.property.PropertyListScreen
import com.glory.chromelabapp.ui.theme.screens.signup.SignupScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_ADD_PROPERTY
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }
        composable(ROUT_SIGNUP) {
            SignupScreen(navController = navController)
        }
        composable(ROUT_LOGIN) {
            LoginScreen(navController = navController)
        }
        composable(ROUT_ADD_PROPERTY) {
            AddPropertyScreen(navController = navController)
        }
        composable(ROUT_VIEW_PROPERTY) {
            PropertyListScreen(navController = navController)
        }
        composable(
            ROUT_EDIT_PROPERTY,
            arguments = listOf(navArgument("propertyId") { type = NavType.StringType })
        ) {
            val propertyId = it.arguments?.getString("propertyId") ?: ""
            EditPropertyScreen(navController, propertyId)
        }



    }
}