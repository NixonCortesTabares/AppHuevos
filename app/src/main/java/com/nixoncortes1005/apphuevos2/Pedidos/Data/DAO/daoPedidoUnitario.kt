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
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.Producto_PedidoUnitarioEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.PedidoUnitarioDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.cantidadesDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.intoPedidosScreenDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.productosdto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoUnitario


@Dao
interface daoPedidoUnitario {

    // devuelve la lista de todos los pedidos unitarios
    @Query("SELECT * FROM PedidoUnitario")
    suspend fun getListaPedidos():List<PedidoUnitarioEntity>

    // insertar un pedido unitario
    /*FUNCION PRINCIPAL PARA INSERTAR UN PEDIDO UNITARIO EN EL SISTEMA*/
    @Transaction
    suspend fun insertPedidoUnitariowithPago(pedido: PedidoUnitarioEntity, pago: PagosEntity, daoPagos: daoPagos): Long{
        val idPedido = insertPedidoUnitario(pedido)
        val idPago = daoPagos.insertPago(pago)
        insertRelacionPedidoPago(PedidoUnitario_PagosEntity(
            idRelacion = 0,
            pedidoUnitarioId = idPedido,
            pagosId = idPago
        ))
        return idPedido
    }

//funcion para insertar un pedido unitario
    @Insert
    suspend fun insertPedidoUnitario(pedido: PedidoUnitarioEntity): Long
//funcion para eliminar un pedido unitario
    @Delete
    suspend fun deletePedidoUnitario(pedido: PedidoUnitarioEntity)

    //funcion para corregir un pedido unitario
    @Query("UPDATE PedidoUnitario SET totalPedido = :nuevoTotal WHERE idPedidoUnitario = :id")
    suspend fun actualizarTotalPedido(id: Long, nuevoTotal: Int)

    @Query(
        """
        SELECT * FROM PedidoUnitario
        WHERE clienteId = :id
    """
    )
    suspend fun getPedidosbyClient(id: Long): List<PedidoUnitario>

    //Funcion Para insertar la relacion entre el pedido unitario y el pago instantaneamente
    @Insert
    suspend fun insertRelacionPedidoPago(relacion: PedidoUnitario_PagosEntity)
    //Funcion Para insertar la relacion entre pedido unitario y Producto
    @Insert
    suspend fun insertRelacionPedidoProducto(relacion: List<Producto_PedidoUnitarioEntity>)

    @Query("SELECT * FROM PedidoUnitario WHERE pedidoSemanalId = :id")
    suspend fun getPedidosUnitariosbyPedidoSemanal(id: Long): List<PedidoUnitarioEntity>

    @Query("""
        SELECT c.idCliente,c.nombre, pu.totalPedido
        FROM PedidoUnitario pu
        INNER JOIN Cliente c ON pu.clienteId = c.idCliente
        WHERE pedidoSemanalId = :id
    """)
    suspend fun getPedidoswithNombre(id: Long): List<intoPedidosScreenDto>

    @Query("""
        SELECT pu.clienteId,pp.cantidadProducto
        FROM Producto_PedidoUnitario pp
        INNER JOIN PedidoUnitario pu ON pp.pedidoUnitarioId = pu.idPedidoUnitario
        WHERE pu.pedidoSemanalId = :id
    """)
    suspend fun  getCantidades(id: Long): List<cantidadesDto>

    @Query("""
        SELECT clienteId, p.nombreProducto
        FROM Producto_PedidoUnitario pp
        INNER JOIN Producto p ON pp.productoId = p.idProducto
        INNER JOIN PedidoUnitario pu ON pp.pedidoUnitarioId = pu.idPedidoUnitario
        WHERE pu.pedidoSemanalId = :id
    """)
    suspend fun getProductos(id: Long): List<productosdto>
}
