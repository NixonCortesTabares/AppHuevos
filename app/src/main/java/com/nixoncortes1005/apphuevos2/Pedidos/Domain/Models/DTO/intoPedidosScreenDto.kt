package com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO

import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Producto

data class intoPedidosScreenDto(
    val idCliente: Long,
    val nombre: String,
    val totalPedido: Int,
    //val cantidadProducto: Float,
    //val productoId: Long

)

