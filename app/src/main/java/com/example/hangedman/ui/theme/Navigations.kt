package com.example.hangedman.ui.theme

sealed class Routes (val route:String){
    object SplashScreen:Routes("splashScreen")
    object MainMenu:Routes("mainMenu")
    object Game:Routes("game/{difficulty}"){
        fun createRoute(difficulty:String)="game/$difficulty"

    }
    object ResultScreen:Routes("result/{resultString}/{difficulty}"){
        fun createRoute(resultString: String,difficulty: String)="result/$resultString/$difficulty"
    }
}
