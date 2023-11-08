package com.example.avance_proyecto.data

import com.example.avance_proyecto.R
import com.example.avance_proyecto.data.model.ConteoEstadoSolicitudItem
import com.example.avance_proyecto.data.uistate.InformationCardUiState
import com.example.avance_proyecto.model.TrackingCard
import com.example.avance_proyecto.ui.theme.*

object TrackingDefaultDataSource {

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

    val itemCardInformation = listOf(
        InformationCardUiState(
            R.string.num_solicitado,"76"
        ),
        InformationCardUiState(
            R.string.nombre_solicitante,"Fabrizzio Quintana"
        ),
        InformationCardUiState(
            R.string.fecha_solicitud,"14/03/2023"
        ),
        InformationCardUiState(
            R.string.predio,"Valle las Esmeraldas"
        ),
        InformationCardUiState(
            R.string.area_predio,"1000 m2"
        ),
        InformationCardUiState(
            R.string.num_casa_habitacion,"4"
        ),
        InformationCardUiState(
            R.string.servicio_solicitado,"Administación: 4, Seguridad: 3, Limpieza:2, Jardineria: 1"
        ),
        InformationCardUiState(
            R.string.area_comun,"200 m2 total: 2"
        )
    )

}