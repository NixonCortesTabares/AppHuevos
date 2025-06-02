package com.nixoncortes1005.apphuevos2.Pedidos.Data.Mapper

import com.nixoncortes1005.apphuevos2.Balance.Data.Entity.BalanceVentasEntity
import com.nixoncortes1005.apphuevos2.Balance.Domain.Models.BalanceVentas
import com.nixoncortes1005.apphuevos2.Control_insumos.Data.Entity.ControlInsumosEntity
import com.nixoncortes1005.apphuevos2.Control_insumos.Data.Entity.InsumosEntity
import com.nixoncortes1005.apphuevos2.Control_insumos.Domain.Models.ControlInsumos
import com.nixoncortes1005.apphuevos2.Control_insumos.Domain.Models.Insumos
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.ClienteEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PagosEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoSemanalEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoUnitarioEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.ProductoEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.Producto_PedidoUnitarioEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.clienteDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Cliente
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Pagos
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoSemanal
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoUnitario
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Producto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.Relations.Producto_PedidoUnitario

fun PedidoUnitarioEntity.toModel(): PedidoUnitario {
    return PedidoUnitario(
        idPedidoUnitario = this.idPedidoUnitario,
        clienteId = this.clienteId,
        fecha = this.fecha,
        pedidoSemanalId = this.pedidoSemanalId,
        totalPedido = this.totalPedido
    )
}

 fun PedidoUnitario.toEntity(): PedidoUnitarioEntity{
     return PedidoUnitarioEntity(
         idPedidoUnitario = this.idPedidoUnitario,
         fecha = this.fecha,
         clienteId = this.clienteId,
         pedidoSemanalId = this.pedidoSemanalId,
         totalPedido = this.totalPedido
     )
 }

fun ClienteEntity.toModel(): Cliente {
    return Cliente(
        idCliente = this.idCliente,
        nombre = this.nombre,
        numTelefono = this.numTelefono
    )
}

fun Cliente.toEntity(): ClienteEntity{
    return ClienteEntity(
        idCliente = this.idCliente,
        nombre = this.nombre,
        numTelefono = this.numTelefono
    )
}

fun PagosEntity.toModel(): Pagos{
    return Pagos(
        idPagos = this.idPagos,
        estado = this.estado,
        cantidad = this.cantidad
    )
}

fun Pagos.toEntity(): PagosEntity{
    return PagosEntity(
        idPagos = this.idPagos,
        estado = this.estado,
        cantidad = this.cantidad
    )
}

fun PedidoSemanalEntity.toModel(): PedidoSemanal{
    return PedidoSemanal(
        idPedidoSemanal = this.idPedidoSemanal,
        fechaCreacion = this.fechaCreacion,
        totalPedidoSemanal = this.totalPedidoSemanal,
        balanceDeVentasId = this.balanceDeVentasId,
        totalEnDeuda = this.totalEnDeuda
    )
}

fun PedidoSemanal.toEntity(): PedidoSemanalEntity{
    return PedidoSemanalEntity(
        idPedidoSemanal = this.idPedidoSemanal,
        fechaCreacion = this.fechaCreacion,
        totalPedidoSemanal = this.totalPedidoSemanal,
        balanceDeVentasId = this.balanceDeVentasId,
        totalEnDeuda = this.totalEnDeuda
    )
}

fun ProductoEntity.toModel(): Producto{
    return Producto(
        idProducto = this.idProducto,
        nombreProducto = this.nombreProducto,
        precioProducto = this.precioProducto
    )
}

fun Producto.toEntity(): ProductoEntity{
    return ProductoEntity(
        idProducto = this.idProducto,
        nombreProducto = this.nombreProducto,
        precioProducto = this.precioProducto
    )
}

fun BalanceVentas.toEntity(): BalanceVentasEntity{
    return BalanceVentasEntity(
        idBalanceVentas = this.idBalanceVentas,
        controlInsumosId = this.ControlInsumosId,
        mes = this.mesCreacion
    )
}

fun BalanceVentasEntity.toModel(): BalanceVentas{
    return BalanceVentas(
        idBalanceVentas = this.idBalanceVentas,
        ControlInsumosId = this.controlInsumosId,
        mesCreacion = this.mes
    )
}

fun Insumos.toEntity(): InsumosEntity{
    return InsumosEntity(
        idInsumos = this.idInsumos,
        nombreInsumo = this.NombreInsumo,
        costo = this.CostoInsumo,
        controlInsumosId = this.controlInsumosId,
        fecha = this.fecha,
    )
}

fun InsumosEntity.toModel(): Insumos{
    return  Insumos(
        idInsumos = this.idInsumos,
        NombreInsumo = this.nombreInsumo,
        CostoInsumo = this.costo,
        controlInsumosId = this.controlInsumosId,
        fecha = this.fecha,
    )
}

fun ControlInsumos.toEntity():ControlInsumosEntity{
    return  ControlInsumosEntity(
        idControlInsumos = this.idControlInsumos,
        totalInsumos = this.TotalInsumos,
        fecha = this.fecha
    )
}

fun ControlInsumosEntity.toModel():ControlInsumos{
    return ControlInsumos(
        idControlInsumos = this.idControlInsumos,
        TotalInsumos = this.totalInsumos,
        fecha = this.fecha
    )
}

fun Producto_PedidoUnitario.toEntity(): Producto_PedidoUnitarioEntity{
    return Producto_PedidoUnitarioEntity(
        productoId = this.idProducto,
        pedidoUnitarioId = this.idPedidoUnitario,
        cantidadProducto = this.cantidadProducto,
        idRelacion = this.idRelacion
    )
}

fun Producto_PedidoUnitarioEntity.toModel(): Producto_PedidoUnitario{
    return Producto_PedidoUnitario(
        idPedidoUnitario = this.pedidoUnitarioId,
        idProducto = this.productoId,
        cantidadProducto = this.cantidadProducto,
        idRelacion = this.idRelacion,
    )
}
