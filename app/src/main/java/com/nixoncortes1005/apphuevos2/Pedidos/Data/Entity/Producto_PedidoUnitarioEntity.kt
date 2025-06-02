package com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Producto_PedidoUnitario",
    foreignKeys = [
        ForeignKey(
            entity = ProductoEntity::class,
            parentColumns = ["idProducto"],
            childColumns = ["productoId"],
            onDelete = ForeignKey.CASCADE
        ),
    ForeignKey(
        entity = PedidoUnitarioEntity::class,
        parentColumns = ["idPedidoUnitario"],
        childColumns = ["pedidoUnitarioId"],
        onDelete = ForeignKey.CASCADE

    )
    ])
data class Producto_PedidoUnitarioEntity(
    @PrimaryKey(autoGenerate = true)val idRelacion: Long,
    val productoId: Long,
    val pedidoUnitarioId: Long,
    val cantidadProducto: Float
)
