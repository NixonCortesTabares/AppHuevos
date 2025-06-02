package com.nixoncortes1005.apphuevos2.Balance.Presentations.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nixoncortes1005.apphuevos2.Balance.Domain.Models.BalanceVentas
import com.nixoncortes1005.apphuevos2.Balance.Domain.Repository.BalanceRepository
import com.nixoncortes1005.apphuevos2.Control_insumos.Domain.Models.ControlInsumos
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoSemanalEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoSemanal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class BalanceViewModel @Inject constructor(private val repository: BalanceRepository): ViewModel() {

    private val _balanceVentas = MutableStateFlow<List<BalanceVentas>>(emptyList())
    val balanceVentas = _balanceVentas

    private val _pedidosperbalance =  MutableStateFlow<List<PedidoSemanal>>(emptyList())
    val pedidosperbalance = _pedidosperbalance

    private val _detbalance = MutableStateFlow<BalanceVentas>(BalanceVentas(0,0,""))
    val detbalance = _detbalance

    private val _balancemensual = MutableStateFlow<BalanceVentas>(BalanceVentas(0,0,"NO EXISTE"))
    val balancemensual = _balancemensual

    fun getBalances(){
        viewModelScope.launch(Dispatchers.IO) {
            _balanceVentas.value = repository.getBalances()
        }
    }
        private fun getFechaHoy(): String{
            val hoy = LocalDate.now()
            val formato = DateTimeFormatter.ofPattern("yyyy-MM")
            return hoy.format(formato)
        }

    private fun insertBalance(){
            viewModelScope.launch(Dispatchers.IO) {
                repository.insertBalancewithControlInsum(
                    mes = getFechaHoy()
                )
            }
    }

  /*  fun deleteBalance(balance: BalanceVentas){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBalance(balance)
        }
    }*/

    /*fun updateBalance(balance: BalanceVentas){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBalance(balance)
        }
    }*/

    fun getPedidosDetBalance(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            _pedidosperbalance.value = repository.getPedidosDetBalance(id)
        }
    }

    fun getBalanceById(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
           _detbalance.value = repository.getBalanceById(id)
        }
    }

    fun getBalanceByMes(){
        viewModelScope.launch(Dispatchers.IO) {
            val mes = getFechaHoy()
            _balancemensual.value = repository.getBalanceByMes(mes)
            if(_balancemensual.value.mesCreacion == "NoExiste")
            {
                insertBalance()
            }
        }
    }


  /*  fun getUltimoBalance(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUltimoBalance()
        }
    }*/
}