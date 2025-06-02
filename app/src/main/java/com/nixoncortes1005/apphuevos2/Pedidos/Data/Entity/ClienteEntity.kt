    package com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity

    import androidx.room.Entity
    import androidx.room.PrimaryKey

    @Entity(tableName = "Cliente")
    data class ClienteEntity(
        @PrimaryKey(autoGenerate = true) val idCliente: Long,
        val nombre: String,
        val numTelefono: String
    )
