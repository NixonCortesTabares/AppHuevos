package com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "PedidoUnitario_PagosEntity",
    foreignKeys = [
        ForeignKey(
            entity = PedidoUnitarioEntity::class,
            parentColumns = ["idPedidoUnitario"],
            childColumns = ["pedidoUnitarioId"],
            onDelete = ForeignKey.CASCADE
        ),
    ForeignKey(
        entity = PagosEntity::class,
        parentColumns = ["idPagos"],
        childColumns = ["pagosId"],
        onDelete = ForeignKey.CASCADE
    )
    ])
data class PedidoUnitario_PagosEntity(
    @PrimaryKey(autoGenerate = true) val idRelacion: Long,
    val pedidoUnitarioId: Long,
    val pagosId: Long
)
