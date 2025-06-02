package com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.ProductoEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Producto

interface ProductoRepository {

    suspend fun  getProductos():List<Producto>

    suspend fun insertProducto(producto: Producto) :Long

    suspend fun getProductobyId(id: Long): Producto

    suspend fun deleteProducto(prod : Producto)

    suspend fun updateProducto(prod: Producto)
}