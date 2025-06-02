package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nixoncortes1005.apphuevos2.Balance.Domain.Repository.BalanceRepository
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoSemanal
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoUnitario
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository.PedidoSemanalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@HiltViewModel
class PedidoSemanalViewModel @Inject constructor(private val repository: PedidoSemanalRepository, private val repositoryBalances: BalanceRepository): ViewModel() {
    private val _pedidos =  MutableStateFlow<List<PedidoSemanal>>(emptyList())
    val pedidos = _pedidos

    private val _pedidoSemanalSelected = MutableStateFlow<PedidoSemanal>(PedidoSemanal(0,"",0,0,0))
    val pedidoSemanalSelected = _pedidoSemanalSelected

    //Pedido a editar
   private val _selectedpedido = MutableStateFlow<List<PedidoUnitario>>(emptyList())
    val selectedpedido = _selectedpedido

    fun getPedidosUnitariosDetSemana(id :Long){
        viewModelScope.launch(Dispatchers.IO) {
            _selectedpedido.value = repository.getPedidosDetSemana(id)
        }
    }

    fun updatePedido(id: Long, nuevoTotal: Int){
        viewModelScope.launch(Dispatchers.IO) {
          repository.updatePedido(id, nuevoTotal)
        }
    }

    private fun getFechaHoy(): String{
        val hoy = LocalDate.now()
        val formato = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        return hoy.format(formato)
    }
    init {
        getPedidos()
    }

    fun getPedidos(){
        viewModelScope.launch(Dispatchers.IO){
           _pedidos.value = repository.getPedidos()
        }
    }

        fun insertPedidoSemanal(){
            viewModelScope.launch(Dispatchers.IO) {
               val pedido = PedidoSemanal(
                   idPedidoSemanal = 0,
                   fechaCreacion = getFechaHoy(),
                   totalPedidoSemanal = 0,
                   balanceDeVentasId = repositoryBalances.getUltimoBalance().idBalanceVentas,
                   totalEnDeuda = 0
               )
              repository.InsertPedido(pedido)
                _pedidos.value = repository.getPedidos()
            }


        }

    fun getPedidoSemanalbyId(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            _pedidoSemanalSelected.value = repository.getPedidoSemanalbyId(id)
        }
    }
}
