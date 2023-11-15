package com.example.avance_proyecto.data.model

data class EstadoSolicitud(
    val data: List<EstadoSolicitudItems> = emptyList(),
    val message: String = "",
    val status: Int = 0
)