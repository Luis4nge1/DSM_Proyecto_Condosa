package com.example.avance_proyecto.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.example.avance_proyecto.data.model.ConteoEstadoSolicitud
import com.example.avance_proyecto.data.model.ConteoEstadoSolicitudItem
import com.example.avance_proyecto.data.model.EstadoSolicitudItems

data class TrackingCard(
    @DrawableRes val imageRes: Int,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color,
    val conteoSolicitudItems: ConteoEstadoSolicitudItem
)
