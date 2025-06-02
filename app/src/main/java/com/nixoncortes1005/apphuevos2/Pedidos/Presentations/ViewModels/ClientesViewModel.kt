package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoUnitario_PagosEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.PedidoUnitarioDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.clienteDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Cliente
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Pagos
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoSemanal
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoUnitario
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository.ClienteRepository
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository.PagosRepository
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository.PedidosUnitariosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ClientesViewModel @Inject constructor(private val repository: ClienteRepository, private val repositoryPU: PedidosUnitariosRepository, private val repositoryPagos: PagosRepository): ViewModel(){
    private val _clientes = MutableStateFlow<List<Cliente>>(emptyList())
    val clientes = _clientes

    private val _clientepedidoinfo = MutableStateFlow<List<PedidoUnitario>>(emptyList())
    val clientepedidoinfo = _clientepedidoinfo

    private val _deudatotal = MutableStateFlow<Int>(0)
    val deudatotal = _deudatotal

    private val _clienteinfo = MutableStateFlow<Cliente>(Cliente(0,"null", "null"))
    val clienteinfo = _clienteinfo

    private val _clientescondeuda = MutableStateFlow<List<clienteDto>>(emptyList())
    val clientescondeuda = _clientescondeuda

    private var _clienteguardado = mutableStateOf<clienteDto>(clienteDto(
        idCliente = 1,
        nombre = "",
        numTelefono = "",
        deuda = 0
    ))
    var clienteguardado = _clienteguardado


    fun getpedidosCliente(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            _clientepedidoinfo.value = repositoryPU.getPedidosbyClient(id)
        }
    }

    fun getClientesConDeuda(){
        viewModelScope.launch(Dispatchers.IO) {
            _clientescondeuda.value = repository.obtenerClientesConDeuda()

        }
    }

    fun getClienteAMostrar(clienteId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val lista = repository.obtenerClientesConDeuda()
            val cliente = lista.find { it.idCliente == clienteId }

            withContext(Dispatchers.Main) {
                if (cliente != null) {
                    _clienteguardado.value = cliente
                } else {
                    Log.e("ClientesViewModel", "Cliente con id $clienteId no encontrado.")
                }
            }
        }
    }



    fun actualizarClienteMostrado(abonoInt: Int){
        viewModelScope.launch{
            if((_clienteguardado.value.deuda - abonoInt) < 0){
                _clienteguardado.value = _clienteguardado.value.copy(deuda = 0)
            }
            else {
                _clienteguardado.value =
                    _clienteguardado.value.copy(deuda = _clienteguardado.value.deuda - abonoInt)
            }
        }
    }

    fun actualizarNombreYNumClienteMostrado(nombre: String, num:String){
        viewModelScope.launch {
            _clienteguardado.value = _clienteguardado.value.copy(nombre = nombre, numTelefono = num)
        }
    }

    fun getClientes(){
        viewModelScope.launch(Dispatchers.IO){
            _clientes.value = repository.getClientes()
        }
    }
    init {
            getClientes()
    }

    fun insertClientes(cliente: Cliente){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCliente(cliente)
        }
    }

    fun deleteCliente(cliente: Cliente){
        viewModelScope.launch(Dispatchers.IO)  {
            repository.deleteCliente(cliente)
        }
    }


   /* fun getInfoCliente(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            _clienteinfo.value = repository.getClienteById(id)
        }

    }*/


    fun calcularCostoPedidoNuevo(abonoInt: Int){

       viewModelScope.launch(Dispatchers.IO)  {
           var valor = abonoInt
           for (pedido in _clientepedidoinfo.value) {
               if (valor == 0) break
               if (pedido.totalPedido == 0) continue

               val pagoAplicado = when {
                   valor < pedido.totalPedido -> valor
                   else -> pedido.totalPedido
               }

               val idPago = repositoryPagos.insertPago(
                   Pagos(
                       idPagos = 0,
                       estado = true,
                       cantidad = pagoAplicado
                   )
               )

               repositoryPagos.insertRelacionPedidoPago(
                   PedidoUnitario_PagosEntity(
                       idRelacion = 0,
                       pedidoUnitarioId = pedido.idPedidoUnitario,
                       pagosId = idPago
                   )
               )

               repositoryPU.actualizarTotalPedido(pedido.idPedidoUnitario, pedido.totalPedido - pagoAplicado)
               repository.actualizarTotalPedido(pedido.pedidoSemanalId, pagoAplicado)

               valor -= pagoAplicado
           }

           if (_clientepedidoinfo.value.isNotEmpty()) {
               val clienteId = _clientepedidoinfo.value.first().clienteId
               _clientepedidoinfo.value = repositoryPU.getPedidosbyClient(clienteId)
           }


       }
    }

    fun actualizarDatosCliente(id:Long, nombre:String, tel: String){
        viewModelScope.launch(Dispatchers.IO)  {
            repository.actualizarDatosCliente(id, nombre, tel)
        }
    }
    }
