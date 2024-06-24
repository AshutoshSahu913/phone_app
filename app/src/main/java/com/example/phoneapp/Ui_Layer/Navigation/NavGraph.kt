package com.example.phoneapp.Ui_Layer.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.phoneapp.Ui_Layer.ContactState
import com.example.phoneapp.Ui_Layer.Screens.HomeScreen
import com.example.phoneapp.Ui_Layer.Screens.ProfileScreen
import com.example.phoneapp.Ui_Layer.Screens.SplashScreen
import com.example.phoneapp.Ui_Layer.ViewModel.ContactViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController,
    viewModel: ContactViewModel,
    state: ContactState,
) {

    val userId = viewModel.userIdFlow.collectAsState().value
    val userName = viewModel.userNameFlow.collectAsState().value
    val userPhone = viewModel.userPhoneFlow.collectAsState().value
    val userEmail = viewModel.userEmailFlow.collectAsState().value
    val userImg = viewModel.userImgFlow.collectAsState().value

    NavHost(navController = navHostController, startDestination = Routes.SplashScreen.route) {
        composable(Routes.HomeScreen.route) {
            HomeScreen(
                state = state,
                navController = navHostController,
                viewModel = viewModel,
                userName = userName,
                userPhone = userPhone,
                userEmail = userEmail,
                userImg = userImg
            )
        }
        composable(Routes.SplashScreen.route) {
            SplashScreen(navHostController = navHostController, userId = userId)
        }

        composable(Routes.ProfileScreen.route) {
            if (userId == 1) {
                HomeScreen(
                    state = state,
                    navController = navHostController,
                    viewModel = viewModel,
                    userName = userName,
                    userPhone = userPhone,
                    userEmail = userEmail,
                    userImg = userImg
                )
            } else {
                ProfileScreen(navHostController = navHostController, viewModel = viewModel)
            }
        }
    }

}