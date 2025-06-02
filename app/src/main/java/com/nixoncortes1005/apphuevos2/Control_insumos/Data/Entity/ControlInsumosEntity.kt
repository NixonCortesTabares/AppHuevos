package com.nixoncortes1005.apphuevos2.Control_insumos.Data.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "ControlInsumos")
data class ControlInsumosEntity (
    @PrimaryKey(autoGenerate = true) val idControlInsumos: Long,
    val fecha: String,
    val totalInsumos: Int

)
