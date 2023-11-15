package com.example.avance_proyecto.domain
import com.example.avance_proyecto.data.model.InformacionSolicitante
import com.example.avance_proyecto.data.repository.InformacionSolicitanteRepositiry

class InformacionSolicitanteUseCase {
    private val repository = InformacionSolicitanteRepositiry()
    suspend fun getInformacionSolicitanteUC(): Result<InformacionSolicitante> {
        return repository.getInformacionSolicitanteRepository()
    }
}