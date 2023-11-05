package com.example.avance_proyecto.domain

import com.example.avance_proyecto.data.model.ConteoEstadoSolicitud
import com.example.avance_proyecto.data.model.EstadoSolicitud
import com.example.avance_proyecto.data.repository.EstadoSolicitudRepository

class EstadoSolicitudUseCase {

    private val repository = EstadoSolicitudRepository()

    suspend fun getEstadoSolicitudUC(): Result<EstadoSolicitud> {
        return repository.getEstadoSolicitudRepository()
    }

    suspend fun getConteoEstadoSolicitudUC(): Result<ConteoEstadoSolicitud> {
        return repository.getConteoEstadoSolicitudRepository()
    }

}