package com.nixoncortes1005.apphuevos2.Balance.Domain.Repository


import com.nixoncortes1005.apphuevos2.Balance.Data.Entity.BalanceVentasEntity
import com.nixoncortes1005.apphuevos2.Balance.Domain.Models.BalanceVentas
import com.nixoncortes1005.apphuevos2.Control_insumos.Data.Dao.daoInsumos
import com.nixoncortes1005.apphuevos2.Control_insumos.Data.Entity.ControlInsumosEntity
import com.nixoncortes1005.apphuevos2.Control_insumos.Domain.Models.ControlInsumos
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoSemanalEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoSemanal

interface BalanceRepository {

    suspend fun getBalances(): List<BalanceVentas>
    suspend fun updateBalance(balance: BalanceVentas)
    suspend fun deleteBalance(balance: BalanceVentas)
    suspend fun insertBalance(balance: BalanceVentas): Long
    suspend fun insertBalancewithControlInsum(mes: String)
    suspend fun getPedidosDetBalance(id: Long):List<PedidoSemanal>
    suspend fun getBalanceById(id:Long): BalanceVentas
    suspend fun getBalanceByMes(mes: String): BalanceVentas
    suspend fun getUltimoBalance(): BalanceVentas
}