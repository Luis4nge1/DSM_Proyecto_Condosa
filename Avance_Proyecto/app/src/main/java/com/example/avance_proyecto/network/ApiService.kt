package com.example.avance_proyecto.network
import com.example.avance_proyecto.data.model.AreasComunes
import com.example.avance_proyecto.data.model.ConteoEstadoSolicitud
import com.example.avance_proyecto.data.model.EstadoSolicitud
import com.example.avance_proyecto.data.model.InformacionSolicitud
import com.example.avance_proyecto.data.model.SolicitudesEstado
import com.example.avance_proyecto.data.model.InformacionPredio
import com.example.avance_proyecto.data.model.InformacionSolicitante
import com.example.avance_proyecto.data.model.SolicitudEstadoSolDTO
import com.example.avance_proyecto.data.model.SolicitudEstadoSolicitud
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


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

    @GET("/area_comun")
    suspend fun  listAreasComunesApiService(): AreasComunes

    @POST("/informacion_solicitante")
    suspend fun  insertInformacionSolicitanteApiService(@Body ses: SolicitudEstadoSolDTO): SolicitudEstadoSolicitud

}
