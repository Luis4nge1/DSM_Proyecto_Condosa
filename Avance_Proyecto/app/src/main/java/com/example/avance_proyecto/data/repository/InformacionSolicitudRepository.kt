package com.example.avance_proyecto.data.repository

import com.example.avance_proyecto.data.model.InformacionSolicitud
import com.example.avance_proyecto.data.model.InformacionSolicitudItem
import com.example.avance_proyecto.data.model.SolicitudesEstado
import com.example.avance_proyecto.network.ApiInstance

class InformacionSolicitudRepository {

    private val service = ApiInstance.apiInstance

    suspend fun getInformacionSolicitudRepository() : Result<InformacionSolicitud>{
        return try {
            val response = service.listInformacionSolicitudApiService()
            //print("La data es $response") //No extrae toda la informacion. Lo trae filtrado
            Result.success(response)
        }catch (e: Exception){
            Result.failure(e)
        }
    }

}