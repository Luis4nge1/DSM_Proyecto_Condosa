package com.example.avance_proyecto.domain

import com.example.avance_proyecto.data.model.InformacionSolicitud
import com.example.avance_proyecto.data.model.InformacionSolicitudItem
import com.example.avance_proyecto.data.model.SolicitudesEstado
import com.example.avance_proyecto.data.repository.InformacionSolicitudRepository
import com.example.avance_proyecto.data.repository.SolicitudesEstadoRepository

class InformacionSolicitudUseCase {

    private val repository = InformacionSolicitudRepository()

    suspend fun getInformacionSolicitudUC(): Result<InformacionSolicitud> {
        //println("USE CASE:"+repository.getInformacionSolicitudRepository())
        return repository.getInformacionSolicitudRepository()
    }
}