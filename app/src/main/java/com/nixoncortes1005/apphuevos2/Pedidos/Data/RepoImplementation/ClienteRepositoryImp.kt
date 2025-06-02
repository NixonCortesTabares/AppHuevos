package com.nixoncortes1005.apphuevos2.Pedidos.Data.RepoImplementation

import android.util.Log
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoCliente
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.ClienteEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Mapper.toEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Mapper.toModel
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.clienteDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Cliente
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository.ClienteRepository
import javax.inject.Inject

class ClienteRepositoryImp @Inject constructor(private val dao: daoCliente):ClienteRepository {
    override suspend fun insertCliente(cliente: Cliente):Long {
        return dao.insertCliente(cliente.toEntity())
    }

    override suspend fun deleteCliente(cliente: Cliente) {
        dao.deleteCliente(cliente.toEntity())
    }

    override suspend fun getClientes(): List<Cliente> {
        return try {
            dao.getClientes().map { it.toModel() }
        }
        catch (e: Throwable){
            Log.d("error en Cliente Repository", e.message.toString()
            )
            emptyList()
        }
    }

    override suspend fun updateCliente(cliente: Cliente) {
        dao.updateCliente(cliente.toEntity())
    }

    override suspend fun getClienteById(id: Long): Cliente {
        return try {
            dao.getClienteById(id).toModel()
        }
        catch (e: Throwable){
            Log.d("Error en ClienteRepository", e.message.toString())
            Cliente(0,"null","null")
        }
    }

    override suspend fun obtenerClientesConDeuda(): List<clienteDto> {
        return try {
            dao.obtenerClientesConDeuda()
        }
        catch (e: Throwable){
            Log.d("Error obteniendo Clientes Con deuda", e.message.toString())
            emptyList()
        }
    }

    override suspend fun actualizarDatosCliente(id: Long, nombre: String, numTel: String) {
        dao.actualizarDatosCliente(id, nombre, numTel)
    }

    override suspend fun actualizarTotalPedido(id: Long, valor: Int) {
        dao.actualizarTotalPedido(id, valor)
    }
}