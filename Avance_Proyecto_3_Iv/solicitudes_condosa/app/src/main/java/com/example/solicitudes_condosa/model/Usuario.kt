package com.example.solicitudes_condosa.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Usuario(
    @StringRes val nombrecompleto: Int,
    @StringRes val puesto: Int,
    @StringRes val predio: Int,
    @StringRes val ubicacion: Int,
    @StringRes val tiempo: Int,
    /*,
    @DrawableRes val imageRes: Int*/
)