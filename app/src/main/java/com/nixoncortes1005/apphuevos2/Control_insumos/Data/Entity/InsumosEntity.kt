package com.nixoncortes1005.apphuevos2.Control_insumos.Data.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Insumos",
    foreignKeys = [
        ForeignKey(
            entity = ControlInsumosEntity::class,
            parentColumns = ["idControlInsumos"],
            childColumns = ["controlInsumosId"],
            onDelete = ForeignKey.CASCADE

        )
    ])
data class InsumosEntity(
    @PrimaryKey(autoGenerate = true) val idInsumos: Long,
    val nombreInsumo: String,
    val costo: Int,
    val controlInsumosId: Long,
    val fecha: String
)
