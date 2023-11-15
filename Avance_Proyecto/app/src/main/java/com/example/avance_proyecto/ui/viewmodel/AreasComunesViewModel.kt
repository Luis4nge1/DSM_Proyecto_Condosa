package com.example.avance_proyecto.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avance_proyecto.data.model.AreasComunes
import com.example.avance_proyecto.data.model.AreasComunesItem
import com.example.avance_proyecto.data.model.InformacionSolicitante
import com.example.avance_proyecto.data.model.InformacionSolicitanteItem
import com.example.avance_proyecto.domain.AreasComunesUseCase
import com.example.avance_proyecto.domain.InformacionSolicitanteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AreasComunesViewModel: ViewModel() {

    val obtenerDatosUseCase = AreasComunesUseCase()

    private val _areascomunesResult = MutableStateFlow(AreasComunes())
    val areascomunesResult = _areascomunesResult.asStateFlow()

    private val _idpredioResult = MutableStateFlow(listOf<AreasComunesItem>())
    val idPredioResult = _idpredioResult.asStateFlow()

    private val _isError = mutableStateOf(false)
    val isError: State<Boolean> = _isError

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    fun getAreasComunes(nuevoId: String){
        viewModelScope.launch(Dispatchers.IO){
            obtenerDatosUseCase.getAreasComunesUC().onSuccess {
                _areascomunesResult.value = it
                FilterIdPredio(nuevoId)
                //println("FUNCION DENTRO DE VIEW MODEL:$it")
            }.onFailure {
                _isError.value = true
            }
            _isLoading.value = false
        }
    }

    fun FilterIdPredio(nuevoId: String) {
        _idpredioResult.value = areascomunesResult.value.filter { userData ->
            userData.id_predio.lowercase().equals(nuevoId)
        }

    }


}
