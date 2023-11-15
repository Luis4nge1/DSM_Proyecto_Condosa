package com.example.avance_proyecto.data.repository

import com.example.avance_proyecto.data.model.AreasComunes
import com.example.avance_proyecto.data.model.AreasComunesItem
import com.example.avance_proyecto.data.model.InformacionSolicitante
import com.example.avance_proyecto.network.ApiInstance

class AreaComunRepository {

    private val service = ApiInstance.apiInstance
    suspend fun getAreasComunesRepository() : Result<AreasComunes>{
        return try {
            val response = service.listAreasComunesApiService()
            //print("La data es $response") //No extrae toda la informacion. Lo trae filtrado
            Result.success(response)
        }catch (e: Exception){
            Result.failure(e)
        }
    }

}