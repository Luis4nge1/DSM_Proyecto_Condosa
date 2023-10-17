package com.example.avance_proyecto.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

data class TrackingTopic(
    @StringRes val name: Int,
    val cantidad: Int,
    @DrawableRes val imageRes: Int,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color
)
