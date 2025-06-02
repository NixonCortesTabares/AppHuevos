package com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.ClienteEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.clienteDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoUnitario

@Dao
interface daoCliente {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCliente(cliente: ClienteEntity) :Long

    @Query("SELECT * FROM Cliente")
    suspend fun getClientes(): List<ClienteEntity>

    @Delete
    suspend fun deleteCliente(cliente: ClienteEntity)

    @Update
    suspend fun updateCliente(cliente: ClienteEntity)

    @Query("SELECT * FROM Cliente WHERE idCliente = :id")
    suspend fun getClienteById(id: Long):ClienteEntity

    @Query("""
        SELECT c.idCliente, c.nombre, c.numTelefono, 
               IFNULL(SUM(p.totalPedido), 0) AS deuda
        FROM Cliente c
        LEFT JOIN PedidoUnitario p ON c.idCliente = p.clienteId
        GROUP BY c.idCliente
    """)
    suspend fun obtenerClientesConDeuda(): List<clienteDto>

    @Query("UPDATE Cliente SET nombre = :nombre, numTelefono = :numTel WHERE idCliente = :id")
    suspend fun actualizarDatosCliente(id: Long, nombre: String, numTel: String)

    @Query("UPDATE Pedido_Semanal SET totalEnDeuda = (totalEnDeuda - :valor), totalPedidoSemanal = (totalPedidoSemanal + :valor) WHERE idPedidoSemanal = :id")
    suspend fun actualizarTotalPedido(id:Long, valor: Int)
}