package com.example.avance_proyecto.data.model

data class EstadoSolicitud(
    val data: List<EstadoSolicitudItems>,
    val message: String,
    val status: Int
)