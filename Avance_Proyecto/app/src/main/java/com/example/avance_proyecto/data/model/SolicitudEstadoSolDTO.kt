package com.example.avance_proyecto.data.model

data class SolicitudEstadoSolDTO(
    val fecha: String,
    val id_solicitud: Int,
    val id_estado_solicitud: Int,
    val ind_ultimo: String
)