package com.nixoncortes1005.apphuevos2.Pedidos.Data.RepoImplementation

import android.util.Log
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoPedidoSemanal
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Mapper.toEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Mapper.toModel
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoSemanal
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoUnitario
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository.PedidoSemanalRepository
import javax.inject.Inject

class PedidoSemanalRepositoryImp @Inject constructor(private val dao: daoPedidoSemanal): PedidoSemanalRepository {
    override suspend fun InsertPedido(pedido: PedidoSemanal): Long {
        return dao.InsertPedido(pedido.toEntity())
    }

    override suspend fun DeletePedido(pedido: PedidoSemanal) {
        dao.DeletePedido(pedido.toEntity())
    }

    override suspend fun getPedidos(): List<PedidoSemanal> {
       return dao.getPedidos().map { it.toModel() }
    }

    override suspend fun updatePedido(id:Long, nuevoTotal: Int) {
        dao.updatePedido(id, nuevoTotal)
    }

    override suspend fun getPedidosDetSemana(id: Long): List<PedidoUnitario> {
        return try {
            dao.getPedidosDetSemana(id).map { it.toModel() }
        }
        catch (e: Throwable){
            Log.e("Fallo en PedidoSemanalRepository", e.message.toString())
            emptyList()
        }
    }

    override suspend fun getPedidoSemanalbyId(id: Long): PedidoSemanal {
        return try {
            dao.getPedidoSemanalbyId(id).toModel()
        }
        catch (e: Throwable){
            Log.e("Error en pedidoSemanal Repository", e.message.toString())
            PedidoSemanal(0,"ERROR",0,0,0)
        }
    }
}