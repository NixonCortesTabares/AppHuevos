package com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pagos")
data class PagosEntity(
    @PrimaryKey(autoGenerate = true) val idPagos: Long,
    val estado: Boolean = false,
    val cantidad: Int
)
