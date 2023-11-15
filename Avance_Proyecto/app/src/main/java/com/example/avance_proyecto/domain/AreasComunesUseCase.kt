package com.example.avance_proyecto.domain

import com.example.avance_proyecto.data.model.AreasComunes
import com.example.avance_proyecto.data.repository.AreaComunRepository

class AreasComunesUseCase {
    private val repository = AreaComunRepository()
    suspend fun getAreasComunesUC(): Result<AreasComunes> {
        return repository.getAreasComunesRepository()
    }
}