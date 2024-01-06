package com.example.hangedman.ui.theme

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ResultScreen(navController: NavController, result: String, difficulty:String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = result,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 2.0.em,
            modifier = Modifier.padding(20.dp)
        )
        Button(
            modifier = Modifier
                .background(Color(0x1F4FD5)),
            onClick = { navController.navigate("mainMenu") {
            popUpTo(Routes.MainMenu.route)
        }

        }) {
            Text(text = "Menú")
        }
        Button(
            modifier = Modifier
                .background(Color(0x1F4FD5)),
            onClick = {
            navController.navigate(Routes.Game.createRoute(difficulty)) {
                popUpTo(Routes.MainMenu.route)
            }
        }) {
            Text(text = "Volver a jugar")
        }
    }

}
