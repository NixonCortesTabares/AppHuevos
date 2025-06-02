package com.nixoncortes1005.apphuevos2.Control_insumos.Data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nixoncortes1005.apphuevos2.Control_insumos.Data.Entity.ControlInsumosEntity
import com.nixoncortes1005.apphuevos2.Control_insumos.Data.Entity.InsumosEntity

@Dao
interface daoInsumos {

    @Insert
    suspend fun insertControlInsumoMensual(insumo: ControlInsumosEntity): Long

    @Insert
    suspend fun insertInsumo(insumo: InsumosEntity) :Long

    @Query("SELECT * FROM ControlInsumos")
    suspend fun getListControlInsumosMensuales(): List<ControlInsumosEntity>

    @Query("SELECT * FROM Insumos WHERE controlInsumosId = :id")
    suspend fun getListInsumosUnitariosbyId(id: Long): List<InsumosEntity>

    @Delete
    suspend fun deleteInsumoUnitario(insumo: InsumosEntity)

    @Query("UPDATE Insumos SET nombreInsumo = :nombreNuevo, costo = :costoNuevo WHERE idInsumos = :idInsumo")
    suspend fun updateInsumoUnitario(idInsumo:Long, nombreNuevo: String, costoNuevo: Int)
}