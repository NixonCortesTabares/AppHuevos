package com.nixoncortes1005.apphuevos2.Control_insumos.Presentations.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nixoncortes1005.apphuevos2.Control_insumos.Domain.Models.ControlInsumos
import com.nixoncortes1005.apphuevos2.Control_insumos.Domain.Models.Insumos
import com.nixoncortes1005.apphuevos2.Control_insumos.Domain.Repository.InsumosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InsumosViewModel  @Inject constructor(private val repo: InsumosRepository): ViewModel(){

    private val _controlInsumos = MutableStateFlow<List<ControlInsumos>>(emptyList())
    val controlInsumos = _controlInsumos

    private val _insumosUnitariosbyId = MutableStateFlow<List<Insumos>>(emptyList())
    val insumosUnitariosbyId = _insumosUnitariosbyId

    fun insertInsumo(insumo: Insumos){
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertInsumo(insumo)
        }
    }

    fun deleteInsumo( insumo: Insumos){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteInsumoUnitario(insumo)
        }
    }

    fun updateInsumo(id: Long, nombreNuevo: String, costoNuevo: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateInsumoUnitario(
                idInsumo = id,
                nombreNuevo = nombreNuevo,
                costoNuevo = costoNuevo
            )
        }
    }

    fun getInsumosUnitariosbyId(id:Long){
        viewModelScope.launch(Dispatchers.IO) {
           _insumosUnitariosbyId.value = repo.getListInsumosUnitariosbyId(id)
        }
    }

    fun getListInsumosMensuales(){
        viewModelScope.launch(Dispatchers.IO) {
            _controlInsumos.value = repo.getListControlInsumosMensuales()
        }
    }
}