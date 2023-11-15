package com.example.avance_proyecto.domain
import com.example.avance_proyecto.data.model.InformacionPredio
import com.example.avance_proyecto.data.repository.InformacionPredioRepository
class InformacionPredioUseCase {

    private val repository = InformacionPredioRepository()
    suspend fun getInformacionPredioUC(): Result<InformacionPredio> {
        return repository.getInformacionPredioRepository()
    }
}