package com.nixoncortes1005.apphuevos2.Control_insumos.Domain.Repository
import com.nixoncortes1005.apphuevos2.Control_insumos.Domain.Models.ControlInsumos
import com.nixoncortes1005.apphuevos2.Control_insumos.Domain.Models.Insumos

interface InsumosRepository {

    suspend fun insertControlInsumoMensual(insumo: ControlInsumos)
    suspend fun insertInsumo(insumo: Insumos) :Long
    suspend fun getListControlInsumosMensuales(): List<ControlInsumos>
    suspend fun getListInsumosUnitariosbyId(id: Long): List<Insumos>
    suspend fun deleteInsumoUnitario(insumo: Insumos)
    suspend fun updateInsumoUnitario(idInsumo:Long, nombreNuevo: String, costoNuevo: Int)
}