package com.example.hangedman

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Game(navController: NavController) {
    var wordToGuess = "ADIVINAME"
    var remainingFails by remember { mutableIntStateOf(5) }
    var hiddenText by remember { mutableStateOf(hideWord(wordToGuess.uppercase())) }
    var buttonStates by remember {
        mutableStateOf(
            ('A'..'Z').plus('Ñ').associateWith { true }
        )
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = hiddenText, fontSize = 40.sp, modifier = Modifier.padding(20.dp))
        Image(
            painterResource(id = R.drawable.logo_game),
            contentDescription = "hangedMan",
            modifier = Modifier.padding(20.dp)
        )

        for (row in listOf("QWERTY", "UIOPAS", "DFGHJK", "LÑZXCV", "BNM")) {
            Row {
                for (letter in row) {
                    val isEnabled = buttonStates[letter]?:false
                    Button(onClick = {
                        hiddenText =
                            check(hiddenText, wordToGuess, letter)
                        if (checkIfFail(wordToGuess, letter)) {
                            remainingFails--
                        }
                        buttonStates = buttonStates.toMutableMap().apply {
                            this[letter] = false
                        }

                        if(remainingFails==0){
                        }
                        if (hiddenText==wordToGuess){

                        }
                    }, enabled = isEnabled) {
                        Text(text = letter.toString())
                    }
                }
            }
        }

        Text(
            text = "Fallos restantes = $remainingFails",
            fontSize = 30.sp,
            modifier = Modifier.padding(20.dp)
        )
    }
}


fun hideWord(wordToHide: String): String {
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



