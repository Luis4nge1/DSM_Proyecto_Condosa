package com.example.avance_proyecto.ui.viewmodel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avance_proyecto.data.model.InformacionSolicitante
import com.example.avance_proyecto.data.model.InformacionSolicitanteItem
import com.example.avance_proyecto.domain.InformacionSolicitanteUseCase //ss
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
class InformacionSolicitanteViewModel: ViewModel() {

    val obtenerDatosUseCase = InformacionSolicitanteUseCase()

    private val _informacionSolicitanteResult = MutableStateFlow(InformacionSolicitante())
    val informacionSolicitanteResult = _informacionSolicitanteResult.asStateFlow()

    private val _idSolicitantedResult = MutableStateFlow(listOf<InformacionSolicitanteItem>())
    val idSolicitanteResult = _idSolicitantedResult.asStateFlow()

    private val _isError = mutableStateOf(false)
    val isError: State<Boolean> = _isError

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    /*init{
        getInformacionSolicitud()
    }*/

    fun getInformacionSolicitante(nuevoId: String){
        viewModelScope.launch(Dispatchers.IO){
            obtenerDatosUseCase.getInformacionSolicitanteUC().onSuccess {
                _informacionSolicitanteResult.value = it
                FilterIdSolicitud(nuevoId)
                //println("FUNCION DENTRO DE VIEW MODEL:$it")
            }.onFailure {
                _isError.value = true
            }
            _isLoading.value = false
        }
    }

    fun FilterIdSolicitud(nuevoId: String) {
        _idSolicitantedResult.value = informacionSolicitanteResult.value.filter { userData ->
            userData.idsolicitante.lowercase().equals(nuevoId)
        }

    }

}