package com.example.avance_proyecto.data.model

data class SolicitudESItem(
    val fecha: String,
    val id_estado_solicitud: Int,
    val id_solicitud: Int,
    val id_solicitud_estado_solicitud: Int,
    val ind_ultimo: String
)