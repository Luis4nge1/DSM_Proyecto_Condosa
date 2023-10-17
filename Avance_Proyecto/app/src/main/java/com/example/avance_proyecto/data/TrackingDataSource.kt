package com.example.avance_proyecto.data

import com.example.avance_proyecto.R
import com.example.avance_proyecto.model.TrackingTopic
import com.example.avance_proyecto.ui.theme.*

object TrackingDataSource {
    val itemCardTracking = listOf(
        TrackingTopic(
            R.string.pendiente, 58, R.drawable.pendiente_image,
            BlueViolet1,
            BlueViolet2,
            BlueViolet3
        ),
        TrackingTopic
            (R.string.cotizado, 58, R.drawable.cotizado_image,
            LightGreen1,
            LightGreen2,
            LightGreen3
                    ),
        TrackingTopic(
            R.string.observado, 58, R.drawable.observado_image,
            OrangeYellow1,
            OrangeYellow2,
            OrangeYellow3
        ),
        TrackingTopic(
            R.string.anulado, 58, R.drawable.anulado_image,
            Beige1,
            Beige2,
            Beige3
        )
    )

}