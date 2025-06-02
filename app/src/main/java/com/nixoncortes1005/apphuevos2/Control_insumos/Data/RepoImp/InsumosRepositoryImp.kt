package com.nixoncortes1005.apphuevos2.Control_insumos.Data.RepoImp

import android.util.Log
import com.nixoncortes1005.apphuevos2.Control_insumos.Data.Dao.daoInsumos
import com.nixoncortes1005.apphuevos2.Control_insumos.Domain.Models.ControlInsumos
import com.nixoncortes1005.apphuevos2.Control_insumos.Domain.Models.Insumos
import com.nixoncortes1005.apphuevos2.Control_insumos.Domain.Repository.InsumosRepository
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Mapper.toEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Mapper.toModel
import javax.inject.Inject

class InsumosRepositoryImp @Inject constructor( private val dao: daoInsumos): InsumosRepository {
    override suspend fun insertControlInsumoMensual(insumo: ControlInsumos) {
        dao.insertControlInsumoMensual(insumo.toEntity())
    }

    override suspend fun insertInsumo(insumo: Insumos):Long {
        return dao.insertInsumo(insumo.toEntity())
    }

    override suspend fun getListControlInsumosMensuales(): List<ControlInsumos> {
        return try {
            dao.getListControlInsumosMensuales().map { it.toModel() }
        }
        catch (e: Throwable){
            Log.e("Error en InsumoRepository", e.message.toString())
            emptyList()
        }
    }

    override suspend fun getListInsumosUnitariosbyId(id: Long): List<Insumos> {
        return try{
            dao.getListInsumosUnitariosbyId(id).map { it.toModel() }
        }
        catch (e: Throwable){
            Log.e("Error en InsumosRepository ID", e.message.toString())
            emptyList()
        }
    }

    override suspend fun deleteInsumoUnitario(insumo: Insumos) {
        dao.deleteInsumoUnitario(insumo.toEntity())
    }

    override suspend fun updateInsumoUnitario(
        idInsumo: Long,
        nombreNuevo: String,
        costoNuevo: Int
    ) {
        dao.updateInsumoUnitario(
            idInsumo = idInsumo,
            nombreNuevo = nombreNuevo,
            costoNuevo = costoNuevo
        )
    }
}