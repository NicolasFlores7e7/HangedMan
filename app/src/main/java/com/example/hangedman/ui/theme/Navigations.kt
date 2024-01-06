package com.example.hangedman.ui.theme

sealed class Routes (val route:String){
    object SplashScreen:Routes("splashScreen")
    object MainMenu:Routes("mainMenu")
    object Game:Routes("game")

}