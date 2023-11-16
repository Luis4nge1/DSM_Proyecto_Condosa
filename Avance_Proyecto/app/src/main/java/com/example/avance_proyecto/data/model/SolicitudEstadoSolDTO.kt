package com.example.avance_proyecto.data.model

import java.time.LocalDateTime

data class SolicitudEstadoSolDTO(
    val fecha: LocalDateTime,
    val id_solicitud: Int,
    val id_estado_solicitud: Int,
    val ind_ultimo: String
)