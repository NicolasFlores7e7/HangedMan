package com.example.hangedman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hangedman.ui.theme.HangedManTheme
import com.example.hangedman.ui.theme.ResultScreen
import com.example.hangedman.ui.theme.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HangedManTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.SplashScreen.route
                    ) {
                        composable(Routes.SplashScreen.route) { SplashScreen(navigationController) }
                        composable(Routes.MainMenu.route) { MainMenu(navigationController) }
                        composable(Routes.Game.route,
                            arguments = listOf(navArgument("difficulty"){
                                type = NavType.StringType
                            })
                        ) {backStackEntry ->
                            Game(navigationController,
                                backStackEntry.arguments?.getString("difficulty") ?:"")
                        }
                        composable(
                            Routes.ResultScreen.route,
                            arguments = listOf(navArgument("resultString") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            ResultScreen(
                                navigationController,
                                backStackEntry.arguments?.getString("resultString") ?: "",
                                backStackEntry.arguments?.getString("difficulty") ?: ""
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    HangedManTheme {

    }
}