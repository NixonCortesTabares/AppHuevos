package com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives

data class PedidoSemanal(
    val idPedidoSemanal: Long,
    val fechaCreacion:String,
    val totalPedidoSemanal: Int,
    val balanceDeVentasId: Long,
    val totalEnDeuda: Int
)
