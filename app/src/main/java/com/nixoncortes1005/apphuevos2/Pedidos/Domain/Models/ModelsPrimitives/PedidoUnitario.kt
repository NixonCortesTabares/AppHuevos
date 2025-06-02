package com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives

data class PedidoUnitario(
    val idPedidoUnitario:Long,
    val clienteId:Long,
    val fecha:String,
    val pedidoSemanalId: Long,
    val totalPedido: Int

)




