package com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoPedidoUnitario
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PagosEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoUnitarioEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoUnitario_PagosEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Pagos
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoUnitario

interface PagosRepository {

    suspend fun insertPago(pago : Pagos) :Long
    suspend fun actualizarPago(pago: Pagos)
    suspend fun eliminarPago(pago: Pagos)
    suspend fun getPagos(): List<Pagos>
    suspend fun getPagosById(id: Long): Pagos
    suspend fun relacionarPago(pago: Pagos, pedido: PedidoUnitario)
    suspend fun insertRelacionPedidoPago(relacion: PedidoUnitario_PagosEntity)
}