package com.example.avance_proyecto.data.model

import java.time.LocalDate

data class SolicitudEstadoSolDTO(
    val fecha: LocalDate = LocalDate.now(),
    val id_estado_solicitud: Int,
    val id_solicitud: Int,
    val id_solicitud_estado_solicitud: Int,
    val ind_ultimo: String
)