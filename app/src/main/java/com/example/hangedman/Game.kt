package com.example.hangedman

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hangedman.ui.theme.Routes
import kotlin.random.Random

@Composable
fun Game(navController: NavController, difficulty: String) {
    var remainingFails by remember { mutableIntStateOf(5) }
    var result by remember { mutableStateOf("") }
    var easy = listOf("pensar", "soltar", "fuerte")
    var normal = listOf("importante", "desarrollo", "naturaleza")
    var hard = listOf("responsabilidad", "particularmente", "establecimiento")
    var randomNum by remember { mutableIntStateOf(Random.nextInt(3)) }
    val fonts = FontFamily(
        Font(R.font.aesthetic_rainbow)
    )

    var wordToGuess = when (difficulty) {
        "Fácil" -> (easy[randomNum].uppercase())
        "Normal" -> (normal[randomNum].uppercase())
        else -> (hard[randomNum].uppercase())
    }
    var hiddenText by remember {
        when (difficulty) {
            "Fácil" -> mutableStateOf(hideWord(easy[randomNum].uppercase()))
            "Normal" -> mutableStateOf(hideWord(normal[randomNum].uppercase()))
            else -> mutableStateOf(hideWord(hard[randomNum].uppercase()))
        }
    }


    var buttonStates by remember {
        mutableStateOf(('A'..'Z').plus('Ñ').associateWith { true })
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = hiddenText,
            fontSize = 24.sp,
            fontFamily = fonts,
            modifier = Modifier.padding(20.dp)

        )
        Image(
            painterResource(id = R.drawable.logo_game),
            contentDescription = "hangedMan",
            modifier = Modifier.padding(20.dp)
        )

        for (row in listOf("ABCDEF", "GHIJKL", "MNÑOPQ", "RSTUVW", "XYZ")) {
            Row {
                for (letter in row) {
                    val isEnabled = buttonStates[letter] ?: false
                    var buttonColor by remember { mutableStateOf(Color(0xFF051620)) }

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonColor,
                            disabledContentColor = buttonColor,
                            disabledContainerColor = Color(0xB2051620)
                        ),

                        shape = RectangleShape,
                        border = BorderStroke(1.dp, Color(188, 201, 214, 255)),

                        onClick = {
                            hiddenText = check(hiddenText, wordToGuess, letter)
                            println(hiddenText)
                            if (checkIfFail(wordToGuess, letter)) {
                                remainingFails--
                                buttonColor = Color.Red
                            }else buttonColor = Color.Green
                            buttonStates = buttonStates.toMutableMap().apply {
                                this[letter] = false
                            }

                            if (remainingFails == 0) {
                                result = "Has perdido"
                                navController.navigate(
                                    Routes.ResultScreen.createRoute(
                                        result, difficulty
                                    )
                                ) {
                                    popUpTo(Routes.MainMenu.route)
                                }
                            }
                            if (hiddenText == wordToGuess) {
                                result =
                                    "Has ganado!! \n Te han sobrado $remainingFails fallos. \n ENHORABUENA"
                                navController.navigate(
                                    Routes.ResultScreen.createRoute(
                                        result, difficulty
                                    )
                                ) {
                                    popUpTo(Routes.MainMenu.route)
                                }

                            }
                        },
                        enabled = isEnabled,


                        ) {
                        Text(
                            text = letter.toString(),
                            fontFamily = fonts,
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }

        Text(
            text = "Fallos restantes = $remainingFails",
            fontFamily = fonts,
            fontSize = 30.sp,
            modifier = Modifier.padding(20.dp)
        )
    }
}


fun hideWord(wordToHide: String): String {
    println(wordToHide)
    return CharArray(wordToHide.length) { '_' }.joinToString("")
}


fun check(hiddenWord: String, wordToGuess: String, charClicked: Char): String {
    val newHiddenWord = hiddenWord.toCharArray()
    for (i in wordToGuess.indices) {
        if (wordToGuess[i] == charClicked) {
            newHiddenWord[i] = charClicked
        }
    }
    return newHiddenWord.joinToString("")
}

fun checkIfFail(wordToGuess: String, charClicked: Char): Boolean {
    for (i in wordToGuess.indices) {
        if (wordToGuess[i] == charClicked) {
            return false
        }
    }
    return true
}



