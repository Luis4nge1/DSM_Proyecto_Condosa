package com.example.avance_proyecto.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.avance_proyecto.data.model.SolicitudEstadoSolDTO
import com.example.avance_proyecto.ui.theme.Avance_ProyectoTheme
import com.example.avance_proyecto.ui.theme.BackgroundRed
import com.example.avance_proyecto.ui.theme.ButtonColorDefault
import com.example.avance_proyecto.ui.theme.ButtonColorRed
import com.example.avance_proyecto.ui.theme.TextWhite
import com.example.avance_proyecto.ui.viewmodel.SolicitudEstSolViewModel

@Composable
fun AnulacionScreen(
    onDimiss: ()->Unit,
    onPositiveButtonClicked: ()->Unit,
    onNegativeButtonClicked: ()->Unit,
    properties: DialogProperties = DialogProperties(),
    solicitudEstSolViewModel: SolicitudEstSolViewModel,
    insertarDataDTO : SolicitudEstadoSolDTO,
    modifier: Modifier = Modifier
) {
    val popupWidth = 300.dp
    val popupHeight = 200.dp

    // Pantalla roja con un mensaje y dos botones
    Dialog(
        onDismissRequest = { onDimiss() },
        properties = properties
    ){
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
                            solicitudEstSolViewModel.insertProduct(insertarDataDTO)
                            onPositiveButtonClicked()
                            println("LISTOOOOOOO")
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
                            onNegativeButtonClicked()
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

/*@Preview
@Composable
fun AnulacionScreenPreview() {
    Avance_ProyectoTheme {
        AnulacionScreen(
            onDimiss = {},
            onPositiveButtonClicked = {},
            onNegativeButtonClicked = {}
        )
    }
}*/