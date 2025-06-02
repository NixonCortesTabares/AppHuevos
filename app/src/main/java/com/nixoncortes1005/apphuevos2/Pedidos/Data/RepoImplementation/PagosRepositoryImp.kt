package com.nixoncortes1005.apphuevos2.Pedidos.Data.RepoImplementation

import android.util.Log
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoPagos
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoPedidoUnitario
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PagosEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoUnitarioEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoUnitario_PagosEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Mapper.toEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Mapper.toModel
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Pagos
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoUnitario
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository.PagosRepository
import javax.inject.Inject

class PagosRepositoryImp @Inject constructor(private val dao: daoPagos, private val daoPU: daoPedidoUnitario): PagosRepository {
    override suspend fun insertPago(pago: Pagos):Long {
        return dao.insertPago(pago.toEntity())
    }

    override suspend fun actualizarPago(pago: Pagos) {
        dao.actualizarPago(pago.toEntity())
    }

    override suspend fun eliminarPago(pago: Pagos) {
        dao.eliminarPago(pago.toEntity())
    }

    override suspend fun getPagos(): List<Pagos> {
        return try {
            dao.getPagos().map { it.toModel() }
        }
        catch (e: Throwable){
            Log.e("fallo en PagosRepository", e.message.toString())
            emptyList()
        }
    }

    override suspend fun getPagosById(id: Long): Pagos {
        return try {dao.getPagosById(id)}
        catch (e: Throwable){
            Log.e("Error en PagosRepository", e.message.toString())
            Pagos(
                idPagos = 0,
                estado = false,
                cantidad = 0
            )
        }
    }

    override suspend fun relacionarPago(
        pago: Pagos,
        pedido: PedidoUnitario
    ) {
        try {
            dao.relacionarPago(
                pago.toEntity(),
                pedido = pedido.toEntity(),
                dao = daoPU,
            )
        }
        catch (e: Throwable){
            Log.e("Error insertando la relacion con el pedido", e.message.toString())
        }
    }

    override suspend fun insertRelacionPedidoPago(relacion: PedidoUnitario_PagosEntity) {
        dao.insertRelacionPedidoPago(relacion)
    }
}