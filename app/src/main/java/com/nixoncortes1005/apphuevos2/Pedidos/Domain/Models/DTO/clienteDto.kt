package com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO

data class clienteDto(
    val idCliente: Long,
    val nombre: String,
    val numTelefono: String,
    val deuda: Int
)
