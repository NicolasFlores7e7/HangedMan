package com.example.hangedman.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hangedman.R

@Composable
fun ResultScreen(navController: NavController, result: String, difficulty: String) {
    val fonts = FontFamily(
        Font(R.font.aesthetic_rainbow)
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.final_logo),
            contentDescription = "logo",
            modifier = Modifier
                .border(BorderStroke(4.dp, Color(0xFF051620)))
        )
        Text(
            text = result,
            fontFamily = fonts,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 2.0.em,
            modifier = Modifier.padding(20.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF051620)
            ),
            shape = RectangleShape,
            onClick = {
                navController.navigate("mainMenu") {
                    popUpTo(Routes.MainMenu.route)
                }

            }) {
            Icon(painterResource(id = R.drawable.ic_menu), contentDescription = "Volver al menú")
            Text(
                text = "Menú",
                fontFamily = fonts,
                fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF051620)
            ),
            shape = RectangleShape,
            onClick = {
                navController.navigate(Routes.Game.createRoute(difficulty)) {
                    popUpTo(Routes.MainMenu.route)
                }
            }) {
            Icon(
                painterResource(id = R.drawable.ic_play_again),
                contentDescription = "Volver a jugar"
            )
            Text(
                text = "Volver a jugar",
                fontFamily = fonts,
                fontSize = 24.sp
            )
        }
    }
}

