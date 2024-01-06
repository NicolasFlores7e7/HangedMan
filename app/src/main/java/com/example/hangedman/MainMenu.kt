package com.example.hangedman

import android.graphics.ColorSpace.Rgb
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat.ThemeCompat
import androidx.navigation.NavController
import com.example.hangedman.ui.theme.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenu(navController: NavController) {
    var selectedText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val difficulty = listOf("Fácil", "Normal", "Difícil")
    var showNoDiff by remember { mutableStateOf(false) }
    var showHelp by remember { mutableStateOf(false) }
    Column(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .clip(CircleShape)
        )
        Column(Modifier.padding(20.dp)) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = { selectedText = it },
                enabled = false,
                readOnly = true,
                modifier = Modifier
                    .clickable { expanded = true }
                    .fillMaxWidth(),
                placeholder = { Text(text = "Dificultad") }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth(),
            ) {
                difficulty.forEach { difficulty ->
                    DropdownMenuItem(
                        text = { Text(text = difficulty) }, onClick = {
                        expanded = false
                        selectedText = difficulty
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(Color(0x1F4FD5)),
            onClick = {
            if (selectedText != "") {
                navController.navigate(Routes.Game.createRoute(selectedText))
            } else {
                showNoDiff = true

            }
        }) {
            Icon(painterResource(id = R.drawable.ic_play), contentDescription = "Jugar")
            Text(text = "Jugar")
        }
        AlertDialogNoDifficulty(showNoDiff, { showNoDiff = false }, { showNoDiff = false })

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(Color(0x1F4FD5)),
            onClick = { showHelp = true }) {
            Icon(painterResource(id = R.drawable.ic_help), contentDescription = "Ayuda")
            Text(text = "Ayuda")
        }
        AlertDialogHelp(showHelp, onDismiss = { showHelp = false }, { showHelp = false })
    }

}

@Composable
fun AlertDialogNoDifficulty(show: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    if (show) {
        AlertDialog(onDismissRequest = {},
            title = { Text(text = "No has elegido dificultad") },
            text = { Text(text = "Para jugar primero tienes que escoger la dificultad en el menú desplegable de arriba.") },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Volver")

                }
            })
    }
}

@Composable
fun AlertDialogHelp(show: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    if (show) {
        AlertDialog(onDismissRequest = {},
            title = { Text(text = "Ayuda") },
            text = { Text(text = "És el típico juego del colgado, tienes 5 fallos y dependo de la dificultad la palabra será más o menos corta.\n Buena suerte") },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Volver")

                }
            })
    }
}


