package com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity

import android.adservices.adid.AdId
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "PedidoUnitario",
    foreignKeys = [
        ForeignKey(
            entity = ClienteEntity::class,
            parentColumns = ["idCliente"],
            childColumns = ["clienteId"],
            onDelete = ForeignKey.CASCADE
        ),
    ForeignKey(
        entity = PedidoSemanalEntity::class,
        parentColumns = ["idPedidoSemanal"],
        childColumns = ["pedidoSemanalId"],
        onDelete = ForeignKey.CASCADE
    )
    ])
data class PedidoUnitarioEntity(
    @PrimaryKey(autoGenerate = true) val idPedidoUnitario:Long,
    val fecha:String,
    val clienteId:Long,
    val pedidoSemanalId:Long,
    val totalPedido: Int
)