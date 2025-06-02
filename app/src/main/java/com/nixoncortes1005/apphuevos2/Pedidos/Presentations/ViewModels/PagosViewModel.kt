package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoUnitario_PagosEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Pagos
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository.PagosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagosViewModel @Inject constructor(private val repo: PagosRepository): ViewModel() {

    private val _pagos = MutableStateFlow<List<Pagos>>(emptyList())
    val pagos = _pagos

    fun insertPago(pago: Pagos, IdRecibido: (Long) -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            val idgenerado = repo.insertPago(pago)
            IdRecibido(idgenerado)
        }
    }
    fun updatePago(pago: Pagos){
        viewModelScope.launch(Dispatchers.IO) {
            repo.actualizarPago(pago)
        }
    }

    fun eliminarPago(pago: Pagos){
        viewModelScope.launch(Dispatchers.IO) {
            repo.eliminarPago(pago)
        }
    }

    fun getPagos(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
           _pagos.value = repo.getPagos()
        }
    }

    fun insertRelacion(relacion: PedidoUnitario_PagosEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertRelacionPedidoPago(relacion)
        }
    }
}