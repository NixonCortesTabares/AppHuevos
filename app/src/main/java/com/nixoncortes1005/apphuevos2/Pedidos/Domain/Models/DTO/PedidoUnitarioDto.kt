package com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO

data class PedidoUnitarioDto(
    val idPedidoUnitario:Long,
    val fecha:String,
    val clienteId:Long,
    val pedidoSemanalId:Long,
    val cantidad: Int
)
