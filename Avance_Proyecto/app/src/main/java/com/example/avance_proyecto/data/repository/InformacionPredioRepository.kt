package com.example.avance_proyecto.data.repository
import com.example.avance_proyecto.network.ApiInstance
import com.example.avance_proyecto.data.model.InformacionPredio

class InformacionPredioRepository {
    private val service = ApiInstance.apiInstance
    suspend fun getInformacionPredioRepository() : Result<InformacionPredio>{
        return try {
            val response = service.listInformacionPredioApiService()
            //print("La data es $response") //No extrae toda la informacion. Lo trae filtrado
            Result.success(response)
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}