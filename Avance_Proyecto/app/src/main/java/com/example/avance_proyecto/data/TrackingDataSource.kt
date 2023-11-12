package com.example.avance_proyecto.data

import com.example.avance_proyecto.R
import com.example.avance_proyecto.data.model.ConteoEstadoSolicitudItem
import com.example.avance_proyecto.data.model.InformacionSolicitudItem

import com.example.avance_proyecto.data.uistate.InformationCardUiState
import com.example.avance_proyecto.model.TrackingCard
import com.example.avance_proyecto.ui.theme.*

object TrackingDefaultDataSource {

    private var listInformacionSolicitante: List<InformacionSolicitudItem> = emptyList()

    fun setListInformacionSolicitante(list: List<InformacionSolicitudItem>) {
        listInformacionSolicitante = list
    }

    val itemCardTracking = listOf(
        TrackingCard(R.drawable.pendiente_image,//Pendiente
            BlueViolet1,
            BlueViolet2,
            BlueViolet3,
            ConteoEstadoSolicitudItem(0,"Pendiente")
        ),
        TrackingCard(
            R.drawable.cotizado_image,//Cotizado
            LightGreen1,
            LightGreen2,
            LightGreen3,
            ConteoEstadoSolicitudItem(0,"Cotizado")
        ),
        TrackingCard(
            R.drawable.observado_image,//Observado
            OrangeYellow1,
            OrangeYellow2,
            OrangeYellow3,
            ConteoEstadoSolicitudItem(0,"Observado")
        ),
        TrackingCard(
            R.drawable.anulado_image,//Anulado
            Beige1,
            Beige2,
            Beige3,
            ConteoEstadoSolicitudItem(0,"Anulado")
        )
    )

    val itemCardDataStaticTracking = listOf(
        ConteoEstadoSolicitudItem(
            0,
            "Pendientes"
        )
    )

    //val firstItem = listInformacionSolicitante.firstOrNull()

    fun mostrarInformacionSolicitud(): List<InformationCardUiState> {
        val itemCardInformation = listOf(
            InformationCardUiState(
                R.string.num_solicitado, listInformacionSolicitante.firstOrNull()?.numero_solicitud.toString() ?: ""
            ),
            InformationCardUiState(
                R.string.nombre_solicitante, listInformacionSolicitante.firstOrNull()?.Nombre ?: ""
            ),
            InformationCardUiState(
                R.string.fecha_solicitud, listInformacionSolicitante.firstOrNull()?.fecha_solicitud ?: ""
            ),
            InformationCardUiState(
                R.string.predio, listInformacionSolicitante.firstOrNull()?.predio ?: ""
            ),
            InformationCardUiState(
                R.string.area_predio, listInformacionSolicitante.firstOrNull()?.area_predio ?: ""
            ),
            InformationCardUiState(
                R.string.num_casa_habitacion, listInformacionSolicitante.firstOrNull()?.numero_casas.toString() ?: ""
            ),
            InformationCardUiState(
                R.string.servicio_solicitado, listInformacionSolicitante.firstOrNull()?.servicio_solicitado ?: ""
            ),
            InformationCardUiState(
                R.string.area_comun, listInformacionSolicitante.firstOrNull()?.cantidad_areas_comunes.toString() ?: ""
            )
        )
        return itemCardInformation
    }

}