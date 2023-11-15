package com.example.avance_proyecto.network
import com.example.avance_proyecto.data.model.ConteoEstadoSolicitud
import com.example.avance_proyecto.data.model.EstadoSolicitud
import com.example.avance_proyecto.data.model.InformacionSolicitud
import com.example.avance_proyecto.data.model.SolicitudesEstado
import com.example.avance_proyecto.data.model.InformacionPredio
import com.example.avance_proyecto.data.model.InformacionSolicitante
import retrofit2.http.GET


interface ApiService {

    @GET("/estado_solicitud")
    suspend fun listEstadoSolicitudApiService(): EstadoSolicitud

    @GET("/conteo_estado_solicitud")
    suspend fun listConteoEstadoSolicitudApiService(): ConteoEstadoSolicitud

    @GET("/solicitudes_estado")
    suspend fun listSolcitudesEstadoApiService(): SolicitudesEstado

    @GET("/informacion_solicitud")
    suspend fun  listInformacionSolicitudApiService(): InformacionSolicitud
    @GET("/inform_predio")
    suspend fun  listInformacionPredioApiService(): InformacionPredio

    @GET("/informacion_solicitante")
    suspend fun  listInformacionSolicitanteApiService(): InformacionSolicitante
}
