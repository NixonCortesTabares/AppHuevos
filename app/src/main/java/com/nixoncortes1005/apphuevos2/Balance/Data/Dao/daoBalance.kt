package com.nixoncortes1005.apphuevos2.Balance.Data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.nixoncortes1005.apphuevos2.Balance.Data.Entity.BalanceVentasEntity
import com.nixoncortes1005.apphuevos2.Control_insumos.Data.Dao.daoInsumos
import com.nixoncortes1005.apphuevos2.Control_insumos.Data.Entity.ControlInsumosEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoSemanalEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoSemanal

@Dao
interface daoBalance {

    @Query("SELECT * FROM Balance_de_Ventas")
    suspend fun getBalances(): List<BalanceVentasEntity>

    @Update
    suspend fun updateBalance(balance: BalanceVentasEntity)

    @Delete
    suspend fun deleteBalance(balance: BalanceVentasEntity)

    @Insert
    suspend fun insertBalance(balance: BalanceVentasEntity): Long

    @Transaction
    suspend fun insertBalancewithControlInsum(daoInsumos: daoInsumos, mes:String){
        val insumo = ControlInsumosEntity(
            idControlInsumos = 0,
            totalInsumos = 0,
            fecha = mes,
        )
        val idInsumoSemanal = daoInsumos.insertControlInsumoMensual(insumo)
        insertBalance(BalanceVentasEntity(
            controlInsumosId = idInsumoSemanal ,
            mes = mes,
            idBalanceVentas = 0
        ))
    }

    @Query("SELECT * FROM Balance_de_Ventas WHERE idBalanceVentas = :id")
    suspend fun getBalanceById(id:Long): BalanceVentasEntity

    @Query("SELECT * FROM Balance_de_Ventas WHERE mes = :mes")
    suspend fun  getBalanceByMes(mes: String): BalanceVentasEntity

    @Query("SELECT * FROM Pedido_Semanal WHERE balanceDeVentasId = :id")
    suspend fun getPedidosDetBalance(id: Long):List<PedidoSemanalEntity>

    @Query("SELECT * FROM Balance_de_Ventas ORDER BY idBalanceVentas DESC LIMIT 1")
    suspend fun getUltimoBalance(): BalanceVentasEntity
}