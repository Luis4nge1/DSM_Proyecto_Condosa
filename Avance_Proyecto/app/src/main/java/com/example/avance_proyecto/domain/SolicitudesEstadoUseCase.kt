package com.example.avance_proyecto.domain

import com.example.avance_proyecto.data.model.SolicitudesEstado
import com.example.avance_proyecto.data.repository.SolicitudesEstadoRepository

class SolicitudesEstadoUseCase {

    private val repository = SolicitudesEstadoRepository()

    suspend fun getSolicitudEstadoPendienteUC(): Result<SolicitudesEstado> {
        return repository.getSolicitudesEstadoRepository()
    }

}