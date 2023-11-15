package com.example.avance_proyecto.data.repository
import com.example.avance_proyecto.network.ApiInstance
import com.example.avance_proyecto.data.model.InformacionSolicitante

class InformacionSolicitanteRepositiry {
    private val service = ApiInstance.apiInstance
    suspend fun getInformacionSolicitanteRepository() : Result<InformacionSolicitante>{
        return try {
            val response = service.listInformacionSolicitanteApiService()
            //print("La data es $response") //No extrae toda la informacion. Lo trae filtrado
            Result.success(response)
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}