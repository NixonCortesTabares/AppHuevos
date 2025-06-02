package com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoSemanalEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoUnitarioEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoSemanal
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoUnitario

interface PedidoSemanalRepository {

    suspend fun InsertPedido(pedido: PedidoSemanal):Long

    suspend fun DeletePedido(pedido: PedidoSemanal)

    suspend fun getPedidos(): List<PedidoSemanal>

    suspend fun updatePedido(id:Long, nuevoTotal: Int)

    suspend fun  getPedidosDetSemana(id: Long): List<PedidoUnitario>

    suspend fun getPedidoSemanalbyId(id: Long): PedidoSemanal

}