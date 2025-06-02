package com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.Relations


data class Producto_PedidoUnitario(
    val idRelacion: Long,
    val idPedidoUnitario: Long,
    val idProducto:Long,
    val cantidadProducto: Float
)
