package com.example.avance_proyecto.data.uistate

import androidx.annotation.StringRes

data class UsuarioUiState (

    @StringRes
    val nombrecompleto: Int,
    @StringRes
    val puesto: Int,
    @StringRes
    val predio: Int,
    @StringRes
    val ubicacion: Int,
    @StringRes
    val tiempo: Int,
    val data: String
    /*,
    @DrawableRes val imageRes: Int*/

)