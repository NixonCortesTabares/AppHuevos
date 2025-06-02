package com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Producto")
data class ProductoEntity(
    @PrimaryKey(autoGenerate = true) val idProducto: Long,
    val nombreProducto: String,
    val precioProducto: Int,
)
