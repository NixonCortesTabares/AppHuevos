package com.nixoncortes1005.apphuevos2.Balance.Data.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.nixoncortes1005.apphuevos2.Control_insumos.Data.Entity.ControlInsumosEntity

@Entity(tableName = "Balance_de_Ventas",
    foreignKeys = [
        ForeignKey(
            entity = ControlInsumosEntity::class,
            parentColumns = ["idControlInsumos"],
            childColumns = ["controlInsumosId"],
            onDelete = ForeignKey.CASCADE
        )
    ])

data class BalanceVentasEntity(
    @PrimaryKey(autoGenerate = true) val idBalanceVentas: Long,
    val controlInsumosId: Long,
    val mes:String
)
