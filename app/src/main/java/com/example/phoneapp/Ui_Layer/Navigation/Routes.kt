package com.example.phoneapp.Ui_Layer.Navigation

sealed class Routes(var route: String) {
    object HomeScreen : Routes("HomeScreen")
    object AddNewContact : Routes("AddNewContactScreen")
    object SplashScreen:Routes("SplashScreen")
    object ProfileScreen:Routes("ProfileScreen")
}