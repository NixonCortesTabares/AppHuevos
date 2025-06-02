package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.cantidadesDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.productosdto
import com.nixoncortes1005.apphuevos2.ui.theme.CardClientes
import com.nixoncortes1005.apphuevos2.ui.theme.ColorBordes

@Composable
fun PedidoUnitarioCard(
    nombreCliente: String,
    pagoGenerado: Int,
    listaProductos: List<String>,
    cantidades: List<Float>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.5f.dp, ColorBordes, RoundedCornerShape(8.dp)),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(CardClientes)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = nombreCliente,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = formatoMonedaColombiana(pagoGenerado),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(Modifier.height(8.dp))

            listaProductos.zip(cantidades).forEach { (producto, cantidad) ->
                Text(text = "Cantidad de $producto: $cantidad")
            }
        }
    }
}



