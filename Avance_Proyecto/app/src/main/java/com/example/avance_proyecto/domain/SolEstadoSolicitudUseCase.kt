package com.example.avance_proyecto.domain

import com.example.avance_proyecto.data.model.SolicitudEstadoSolDTO
import com.example.avance_proyecto.data.model.SolicitudEstadoSolicitud

class SolEstadoSolicitudUseCase {

    private val repository = SolEstadoSolicitudUseCase()

    suspend fun insertSolEstadoSolicitud(solEstadSolicitudDTO: SolicitudEstadoSolDTO) : Result<SolicitudEstadoSolicitud> {
        return repository.insertSolEstadoSolicitud(solEstadSolicitudDTO)
    }

}