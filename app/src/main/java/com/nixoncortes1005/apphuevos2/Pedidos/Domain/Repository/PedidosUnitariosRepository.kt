package com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoPagos
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PagosEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoUnitarioEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoUnitario_PagosEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.ProductoEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.Producto_PedidoUnitarioEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.PedidoUnitarioDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.cantidadesDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.intoPedidosScreenDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.productosdto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Pagos
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoUnitario
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Producto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.Relations.Producto_PedidoUnitario

interface PedidosUnitariosRepository {

    suspend fun insertPedidoUnitariowithPago(pedido: PedidoUnitario, pago: Pagos): Long
    suspend fun insertPedidoUnitario(pedido: PedidoUnitario) :Long
    suspend fun deletePedidoUnitario(pedido: PedidoUnitario)
    suspend fun actualizarTotalPedido(id: Long, nuevoTotal: Int)
    suspend fun getPedidosbyClient(id: Long): List<PedidoUnitario>
    suspend fun getPedidosUnitariosbyPedidoSemanal(id: Long): List<PedidoUnitario>
    suspend fun insertRelacionPedidoProducto(relacion: List<Producto_PedidoUnitario>)
    suspend fun getPedidoswithNombre(id: Long): List<intoPedidosScreenDto>
    suspend fun getCantidades(id: Long):  Map<Long, List<cantidadesDto>>
    suspend fun getProductos(id: Long): Map<Long, List<productosdto>>

}