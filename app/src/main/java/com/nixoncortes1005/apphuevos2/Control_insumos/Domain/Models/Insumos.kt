package com.nixoncortes1005.apphuevos2.Control_insumos.Domain.Models

data class Insumos(
    val idInsumos: Long,
    val NombreInsumo: String,
    val CostoInsumo: Int,
    val controlInsumosId: Long,
    val fecha: String
)
