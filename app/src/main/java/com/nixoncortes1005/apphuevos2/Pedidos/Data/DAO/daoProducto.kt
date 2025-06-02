package com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.ProductoEntity

@Dao
interface daoProducto {

    @Query("SELECT * FROM Producto")
    suspend fun getProductos(): List<ProductoEntity>

    @Insert
    suspend fun insertProducto(producto: ProductoEntity): Long

    @Query("SELECT * FROM Producto WHERE idProducto = :id")
    suspend fun getProductobyId(id: Long): ProductoEntity

    @Delete
    suspend fun deleteProducto(prod : ProductoEntity)

    @Update
    suspend fun updateProducto(prod: ProductoEntity)


}