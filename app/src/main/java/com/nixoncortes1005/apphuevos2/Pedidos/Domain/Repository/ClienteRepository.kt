package com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository

import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.ClienteEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.clienteDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Cliente

interface ClienteRepository {
    suspend fun insertCliente(cliente: Cliente) :Long
    suspend fun deleteCliente(cliente: Cliente)
    suspend fun getClientes(): List<Cliente>
    suspend fun updateCliente(cliente: Cliente)
    suspend fun getClienteById(id: Long): Cliente
    suspend fun obtenerClientesConDeuda(): List<clienteDto>
    suspend fun actualizarDatosCliente(id: Long, nombre: String, numTel: String)
    suspend fun actualizarTotalPedido(id:Long, valor: Int)
}