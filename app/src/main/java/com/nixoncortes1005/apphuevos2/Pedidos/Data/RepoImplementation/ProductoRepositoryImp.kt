package com.nixoncortes1005.apphuevos2.Pedidos.Data.RepoImplementation

import android.util.Log
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoProducto
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.ProductoEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Mapper.toEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Mapper.toModel
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Producto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository.ProductoRepository
import javax.inject.Inject

class ProductoRepositoryImp @Inject constructor(private val dao: daoProducto): ProductoRepository {
    override suspend fun getProductos(): List<Producto> {
        return try {
            dao.getProductos().map { it.toModel() }
        }
        catch (e: Throwable){
            Log.e("Error en Repository Productos", e.message.toString())
            emptyList()
        }
    }

    override suspend fun insertProducto(producto: Producto): Long {
        return dao.insertProducto(producto.toEntity())
    }

    override suspend fun getProductobyId(id: Long): Producto {
        return dao.getProductobyId(id).toModel()
    }

    override suspend fun deleteProducto(prod: Producto) {
        return dao.deleteProducto(prod.toEntity())
    }

    override suspend fun updateProducto(prod: Producto) {
        return dao.updateProducto(prod.toEntity())
    }
}