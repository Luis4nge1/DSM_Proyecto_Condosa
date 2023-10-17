package com.example.avance_proyecto.data

data class OrderUiState(
    /** nombre del usuario */
    val name: String = "John Doe",
    /** (0 = distribucion, 1 = tendencia, 2 = evolucion) */
    val optionChart: Int = 0,
    val cantidadPendientes: Int = 10,
    val cantidadCotizados: Int = 10,
    val cantidadObservados: Int = 10,
    val cantidadAnulados: Int = 10
)
