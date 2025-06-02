package com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoSemanalEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoUnitarioEntity

@Dao
interface daoPedidoSemanal {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertPedido(pedido: PedidoSemanalEntity):Long

    @Delete
    suspend fun DeletePedido(pedido: PedidoSemanalEntity)

    @Query("SELECT * FROM Pedido_Semanal")
    suspend fun getPedidos(): List<PedidoSemanalEntity>

    @Query("UPDATE Pedido_Semanal SET totalEnDeuda = (totalEnDeuda + :nuevoTotal) WHERE idPedidoSemanal = :id")
    suspend fun updatePedido(id:Long, nuevoTotal: Int)

    //Funcion para tomar una lista de pedidos unitarios de un pedido semanal determinado
    @Query("SELECT * FROM PedidoUnitario WHERE pedidoSemanalId = :id")
    suspend fun  getPedidosDetSemana(id: Long): List<PedidoUnitarioEntity>

    @Query("SELECT * FROM Pedido_Semanal WHERE idPedidoSemanal = :id")
    suspend fun getPedidoSemanalbyId(id: Long): PedidoSemanalEntity
}