package com.example.avance_proyecto.data.uistate

import java.util.Date

data class InformationUiState(
    /** nombre del usuario */
    val nombreSolicitante: String = "John Doe",
    /** (0 = distribucion, 1 = tendencia, 2 = evolucion) */
    val cantidadSolicitud: Int = 0,
    val fechaSolicitud: Date = Date(10/10/23),
    val predio: String = "Valle la Esperanza",
    val areaPredio: String = "10000 m2",
    val numerpHabitaciones: Int = 4,
    val serviciosSolicitados: List<String> = listOf("Administracion: 1", "Seguridad: 3", "Limpieza: 3", "Jardineria: 1"),
    val areasComunes: String = "200 m2 Total: 2"
)
