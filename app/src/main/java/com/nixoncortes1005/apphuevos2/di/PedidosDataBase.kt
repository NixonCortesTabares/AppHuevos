package com.nixoncortes1005.apphuevos2.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nixoncortes1005.apphuevos2.Balance.Data.Dao.daoBalance
import com.nixoncortes1005.apphuevos2.Balance.Data.Entity.BalanceVentasEntity
import com.nixoncortes1005.apphuevos2.Control_insumos.Data.Dao.daoInsumos
import com.nixoncortes1005.apphuevos2.Control_insumos.Data.Entity.ControlInsumosEntity
import com.nixoncortes1005.apphuevos2.Control_insumos.Data.Entity.InsumosEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoCliente
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoPagos
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoPedidoSemanal
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoPedidoUnitario
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoProducto
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.ClienteEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PagosEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoSemanalEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoUnitarioEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoUnitario_PagosEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.ProductoEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.Producto_PedidoUnitarioEntity


@Database(entities = [BalanceVentasEntity::class, ControlInsumosEntity::class, InsumosEntity::class, ClienteEntity::class, PagosEntity::class, PedidoSemanalEntity::class, PedidoUnitario_PagosEntity::class, PedidoUnitarioEntity::class, Producto_PedidoUnitarioEntity::class, ProductoEntity::class ], version = 1)
abstract class PedidosDataBase: RoomDatabase() {
    abstract fun daoPedidoUnitario(): daoPedidoUnitario
    abstract fun daoCliente(): daoCliente
    abstract fun daoPedidoSemanal(): daoPedidoSemanal
    abstract fun daoProducto(): daoProducto
    abstract fun daoInsumos(): daoInsumos
    abstract fun daoBalance(): daoBalance
    abstract fun daoPagos():daoPagos
}