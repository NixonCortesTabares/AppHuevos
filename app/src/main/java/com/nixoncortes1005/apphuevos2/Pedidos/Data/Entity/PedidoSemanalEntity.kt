package com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.nixoncortes1005.apphuevos2.Balance.Data.Entity.BalanceVentasEntity

@Entity(tableName = "Pedido_Semanal",
    foreignKeys = [
        ForeignKey(
            entity = BalanceVentasEntity::class,
            parentColumns = ["idBalanceVentas"],
            childColumns = ["balanceDeVentasId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class PedidoSemanalEntity(
    @PrimaryKey(autoGenerate = true) val idPedidoSemanal: Long,
    val fechaCreacion: String,
    val totalPedidoSemanal: Int,
    val balanceDeVentasId : Long,
    val totalEnDeuda: Int
)
