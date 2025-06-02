package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoPagos
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.PedidoUnitarioDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.cantidadesDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.intoPedidosScreenDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.productosdto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Cliente
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Pagos
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoSemanal
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoUnitario
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Producto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.Relations.Producto_PedidoUnitario
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository.PedidoSemanalRepository
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository.PedidosUnitariosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class PedidoUnitarioViewModel @Inject constructor(private val repo: PedidosUnitariosRepository, private val repoSemanal: PedidoSemanalRepository): ViewModel() {
    private val _listpedidos = MutableStateFlow<List<PedidoUnitario>>(emptyList())
    val listpedidos = _listpedidos

    private val _PUbyClient = MutableStateFlow<List<PedidoUnitario>>(emptyList())
    val PUbyClient = _PUbyClient

    private val _pedidoswithnombre = MutableStateFlow<List<intoPedidosScreenDto>>(emptyList())
    val pedidoswithnombre = _pedidoswithnombre

    private val _listacantidades = MutableStateFlow<Map<Long,List<cantidadesDto>>>(emptyMap())
    val listacantidades = _listacantidades

    private val _listaproductos = MutableStateFlow<Map<Long,List<productosdto>>>(emptyMap())
    val listaproductos = _listaproductos

    fun getCantidadesyProductos(id: Long){
        viewModelScope.launch {
            _listacantidades.value = repo.getCantidades(id)
            _listaproductos.value = repo.getProductos(id)
        }
    }

    fun getPedidosWithNombre(id: Long){
        viewModelScope.launch {
            _pedidoswithnombre.value = repo.getPedidoswithNombre(id)

        }
    }

    fun getPedidosCliente(id: Long){
        viewModelScope.launch {
            _PUbyClient.value = repo.getPedidosbyClient(id)
        }
    }
    private fun getFechaHoy(): String{
        val hoy = LocalDate.now()
        val formato = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        return hoy.format(formato)
    }

    fun getPedidosByPedidoSemanal(id: Long){
        viewModelScope.launch {
            _listpedidos.value = repo.getPedidosUnitariosbyPedidoSemanal(id)
        }
    }


    fun deletePedidoUnitario(pedido: PedidoUnitario){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deletePedidoUnitario(pedido)
        }
    }

    fun actualizarTotalPedido(id: Long, nuevoTotal: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.actualizarTotalPedido(id, nuevoTotal)
        }
    }


    fun insertarOActualizarPedidoUnitario(pedidoSemanalId: Long, totalAnterior: Int, cantidades: Map<Long, String>, cliente: Cliente){
        var lista: List<Producto_PedidoUnitario> = emptyList()
        var check = false
        viewModelScope.launch(Dispatchers.IO) {
            for (pedido in _PUbyClient.value) {
                if (pedido.pedidoSemanalId == pedidoSemanalId) {
                    repo.actualizarTotalPedido(
                        pedido.idPedidoUnitario,
                        pedido.totalPedido + totalAnterior
                    )
                    repoSemanal.updatePedido(pedidoSemanalId, totalAnterior)
                    for (cantidad in cantidades) {
                        val relacion = Producto_PedidoUnitario(
                            idRelacion = 0,
                            idPedidoUnitario = pedido.idPedidoUnitario,
                            idProducto = cantidad.key,
                            cantidadProducto = cantidad.value.toFloat()
                        )
                        lista = lista + relacion
                    }
                    repo.insertRelacionPedidoProducto(lista)
                    check = true
                    break
                }
            }
            if (!check) {

               val idpedido = repo.insertPedidoUnitario(
                    PedidoUnitario(
                        idPedidoUnitario = 0,
                        clienteId = cliente.idCliente,
                        fecha = getFechaHoy(),
                        pedidoSemanalId = pedidoSemanalId,
                        totalPedido = totalAnterior
                    )
                )
                repoSemanal.updatePedido(pedidoSemanalId, totalAnterior)
                    for (cantidad in cantidades) {
                        val relacion = Producto_PedidoUnitario(
                            idRelacion = 0,
                            idPedidoUnitario = idpedido,
                            idProducto = cantidad.key,
                            cantidadProducto = cantidad.value.toFloat()
                        )
                        lista = lista + relacion
                    }
                    repo.insertRelacionPedidoProducto(lista)
            }
        }

    }
}