package com.example.apiapp.domain

import com.example.avance_proyecto.data.DataEstadoSolicitud
import com.example.avance_proyecto.data.repository.EstadoSolicitudRepository

class DataUseCase {

    private val repository = EstadoSolicitudRepository()

    suspend fun getEstadoSolicitud(): Result<DataEstadoSolicitud> {
        return repository.getEstadoSolicitudRepository()
    }

}