package com.nixoncortes1005.apphuevos2.Pedidos.Data.RepoImplementation

import android.util.Log
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoPagos
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoPedidoUnitario
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PagosEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoUnitarioEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoUnitario_PagosEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.Producto_PedidoUnitarioEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Mapper.toEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Mapper.toModel
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.PedidoUnitarioDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.cantidadesDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.intoPedidosScreenDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.productosdto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Pagos
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoUnitario
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Producto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.Relations.Producto_PedidoUnitario
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository.PedidosUnitariosRepository
import javax.inject.Inject

class PedidosUnitariosRepositoryImp @Inject constructor(
    private val dao: daoPedidoUnitario,
    private val daoPagos: daoPagos
) : PedidosUnitariosRepository {

    override suspend fun insertPedidoUnitariowithPago(
        pedido: PedidoUnitario,
        pago: Pagos
    ): Long {
        return dao.insertPedidoUnitariowithPago(
            pedido = pedido.toEntity(),
            pago = pago.toEntity(),
            daoPagos = daoPagos
        )
    }

    override suspend fun insertPedidoUnitario(pedido: PedidoUnitario): Long {
        return dao.insertPedidoUnitario(pedido.toEntity())
    }

    override suspend fun deletePedidoUnitario(pedido: PedidoUnitario) {
        dao.deletePedidoUnitario(pedido.toEntity())
    }

    override suspend fun actualizarTotalPedido(id: Long, nuevoTotal: Int) {
        dao.actualizarTotalPedido(id, nuevoTotal)
    }

    override suspend fun getPedidosbyClient(id: Long): List<PedidoUnitario> {
        val lista = dao.getPedidosbyClient(id)
        return lista
    }

    override suspend fun getPedidosUnitariosbyPedidoSemanal(id: Long): List<PedidoUnitario> {
        return try {
            dao.getPedidosUnitariosbyPedidoSemanal(id).map { it.toModel() }
        } catch (e: Throwable) {
            Log.d("Error en PedidoUnitarioImpRep", e.message.toString())
            emptyList()
        }
    }

    override suspend fun insertRelacionPedidoProducto(relacion: List<Producto_PedidoUnitario>) {
        dao.insertRelacionPedidoProducto(
            relacion = relacion.map { it.toEntity() }
        )
    }

    override suspend fun getPedidoswithNombre(id: Long): List<intoPedidosScreenDto> {
        return try {
            dao.getPedidoswithNombre(id)
        } catch (e: Throwable) {
            Log.e("error en el nuevo dto", e.message.toString())
            emptyList()
        }
    }

    override suspend fun getCantidades(id: Long): Map<Long, List<cantidadesDto>> {

        val cantidades = dao.getCantidades(id)
            .groupBy { it.clienteId }
        return try {
            cantidades
        } catch (e: Throwable) {
            Log.e("Error intentando tomar las cantidades", e.message.toString())
            emptyMap()
        }
    }

    override suspend fun getProductos(id: Long): Map<Long, List<productosdto>> {
        val productos = dao.getProductos(id)
            .groupBy { it.clienteId }
        return try {
            productos
        } catch (e: Throwable) {
            Log.e("Error intentando tomar las cantidades", e.message.toString())
            emptyMap()
        }
    }
}