package com.nixoncortes1005.apphuevos2.Balance.Data.RepoImp

import android.util.Log
import com.nixoncortes1005.apphuevos2.Balance.Data.Dao.daoBalance
import com.nixoncortes1005.apphuevos2.Balance.Domain.Models.BalanceVentas
import com.nixoncortes1005.apphuevos2.Balance.Domain.Repository.BalanceRepository
import com.nixoncortes1005.apphuevos2.Control_insumos.Data.Dao.daoInsumos
import com.nixoncortes1005.apphuevos2.Control_insumos.Data.Entity.ControlInsumosEntity
import com.nixoncortes1005.apphuevos2.Control_insumos.Domain.Models.ControlInsumos
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoSemanalEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Mapper.toEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Mapper.toModel
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoSemanal
import javax.inject.Inject

class BalanceVentasRepoImp @Inject constructor(private val dao: daoBalance, private val daoInsumos: daoInsumos): BalanceRepository {
    override suspend fun getBalances(): List<BalanceVentas> {
        return try {
            dao.getBalances().map { it.toModel() }
        }
        catch (e: Throwable){
            Log.e("error BalanceVentasRepoImp", e.message.toString())
            emptyList()
        }
    }

    override suspend fun updateBalance(balance: BalanceVentas) {
        dao.updateBalance(balance.toEntity())
    }

    override suspend fun deleteBalance(balance: BalanceVentas) {
        dao.deleteBalance(balance.toEntity())
    }

    override suspend fun insertBalance(balance: BalanceVentas):Long {
        return dao.insertBalance(balance.toEntity())
    }

    override suspend fun insertBalancewithControlInsum(
        mes: String
    ) {
        dao.insertBalancewithControlInsum(
            daoInsumos = daoInsumos,
            mes = mes
        )
    }


    override suspend fun getPedidosDetBalance(id: Long): List<PedidoSemanal> {
        return try {
            dao.getPedidosDetBalance(id).map { it.toModel() }
        }
        catch (e: Throwable){
            Log.e("Fallo en BalanceRepo", e.message.toString())
            emptyList()
        }
    }

    override suspend fun getBalanceById(id: Long): BalanceVentas {
       return try {
           dao.getBalanceById(id).toModel()
       }
       catch (e: Throwable){
           Log.e("Error en BalanceRepo getBalanceID", e.message.toString())
           BalanceVentas(0,0,"")
       }
    }

    override suspend fun getBalanceByMes(mes: String): BalanceVentas{
        return try{
            dao.getBalanceByMes(mes).toModel()
        }
        catch (e: Throwable){
            Log.e("No se encontro el balance de este mes, creando uno...", e.message.toString())
            BalanceVentas(0,0,"NoExiste")
        }
    }

    override suspend fun getUltimoBalance(): BalanceVentas {
        return dao.getUltimoBalance().toModel()
    }
}