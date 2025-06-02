package com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PagosEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoUnitarioEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoUnitario_PagosEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Pagos

@Dao
interface daoPagos {

    @Insert
    suspend fun insertPago(pago : PagosEntity):Long

    @Update
    suspend fun actualizarPago(pago: PagosEntity)

    @Delete
    suspend fun eliminarPago(pago: PagosEntity)

    @Query("SELECT * FROM Pagos")
    suspend fun getPagos(): List<PagosEntity>

    //funcion para corregir un pedido unitario
    @Query("SELECT * FROM Pagos WHERE idPagos = :id")
    suspend fun getPagosById(id: Long): Pagos

    @Insert
    suspend fun insertRelacionPedidoPago(relacion: PedidoUnitario_PagosEntity)

    @Transaction
    suspend fun relacionarPago(pago: PagosEntity, pedido: PedidoUnitarioEntity, dao: daoPedidoUnitario){
        val idPago = insertPago(pago)
        dao.insertRelacionPedidoPago(PedidoUnitario_PagosEntity(
            pedidoUnitarioId = pedido.idPedidoUnitario,
            pagosId = idPago,
            idRelacion = 0
        ))

    }
}