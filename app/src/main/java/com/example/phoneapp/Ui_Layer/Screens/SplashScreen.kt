package com.example.phoneapp.Ui_Layer.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.phoneapp.Ui_Layer.Navigation.Routes
import com.example.phoneapp.ui.theme.AppColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController, userId: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Call,
            contentDescription = "Logo",
            tint = Color.White,
            modifier = Modifier.size(200.dp)
        )
    }
    LaunchedEffect(key1 = true) {
        delay(3000)
        if (userId==0) {
            navHostController.navigate(Routes.ProfileScreen.route)
        } else {
            navHostController.navigate(Routes.HomeScreen.route)
        }
    }
}