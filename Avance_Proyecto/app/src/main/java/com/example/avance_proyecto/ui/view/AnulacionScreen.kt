package com.example.avance_proyecto.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.avance_proyecto.R
import com.example.avance_proyecto.ui.theme.Avance_ProyectoTheme
import com.example.avance_proyecto.ui.theme.BackgroundRed
import com.example.avance_proyecto.ui.theme.ButtonColorDefault
import com.example.avance_proyecto.ui.theme.ButtonColorRed
import com.example.avance_proyecto.ui.theme.TextWhite

class AnulacionScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Avance_ProyectoTheme {
                // Llamamos al composable de la pantalla roja
                AnulacionScreen()
            }
        }
    }
}

@Composable
fun AnulacionScreen() {
    val popupWidth = 300.dp
    val popupHeight = 200.dp

    // Pantalla roja con un mensaje y dos botones
    Box(
        modifier = Modifier
            .background(BackgroundRed)
            .width(popupWidth)
            .height(popupHeight),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = buildAnulacionText(),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        // Acción para continuar
                    },
                    colors = ButtonDefaults.buttonColors(
                        ButtonColorDefault,
                        Color.White
                    )
                ) {
                    Text(text = "Continuar")
                }

                Button(
                    onClick = {
                        // Acción para cancelar
                    },
                    colors = ButtonDefaults.buttonColors(
                        ButtonColorRed,
                        Color.White
                    )
                ) {
                    Text(text = "Cancelar")
                }
            }
        }
    }
}

@Composable
fun buildAnulacionText(): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = SpanStyle(color = TextWhite)) {
            append("¿Está seguro de que desea ")
            withStyle(style = SpanStyle(color = ButtonColorRed)) {
                append("ANULAR")
            }
            append(" esta solicitud?")
        }
    }
}

@Preview
@Composable
fun AnulacionScreenPreview() {
    Avance_ProyectoTheme {
        AnulacionScreen()
    }
}
