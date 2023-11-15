package com.example.avance_proyecto.domain

import com.example.avance_proyecto.data.model.SolicitudEstadoSolDTO
import com.example.avance_proyecto.data.model.SolicitudEstadoSolicitud
import com.example.avance_proyecto.data.repository.SolEstadoSolicitudRepository

class SolEstadoSolicitudUseCase {

    private val repository = SolEstadoSolicitudRepository()

    suspend fun insertSolEstadoSolicitud(solEstadSolicitudDTO: SolicitudEstadoSolDTO) : Result<SolicitudEstadoSolicitud> {
        return repository.insertSolEstadoSolicitudRepository(solEstadSolicitudDTO)
    }

}