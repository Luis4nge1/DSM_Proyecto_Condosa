package com.example.avance_proyecto.ui.viewmodel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avance_proyecto.data.model.InformacionPredio
import com.example.avance_proyecto.data.model.InformacionPredioItem
import com.example.avance_proyecto.data.model.InformacionSolicitudItem
import com.example.avance_proyecto.domain.InformacionPredioUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
class InformacionPredioViewModel: ViewModel() {

    val obtenerDatosUseCase = InformacionPredioUseCase()

    private val _informacionPredioResult = MutableStateFlow(InformacionPredio())
    val informacionPredioResult = _informacionPredioResult.asStateFlow()

    private val _isError = mutableStateOf(false)
    val isError: State<Boolean> = _isError

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _idPredioResult = MutableStateFlow(listOf<InformacionPredioItem>())
    val idPredioResult = _idPredioResult.asStateFlow()

    fun getInformacionPredio(nuevoId: String){
        viewModelScope.launch(Dispatchers.IO){
            obtenerDatosUseCase.getInformacionPredioUC().onSuccess {
                _informacionPredioResult.value = it
                FilterIdPredio(nuevoId)
                println(it)
            }.onFailure {
                _isError.value = true
            }
            _isLoading.value = false
        }
    }

    fun FilterIdPredio(nuevoId: String) {
        _idPredioResult.value = _informacionPredioResult.value.filter { userData ->
            userData.idPredio.equals(nuevoId)
        }

    }
}