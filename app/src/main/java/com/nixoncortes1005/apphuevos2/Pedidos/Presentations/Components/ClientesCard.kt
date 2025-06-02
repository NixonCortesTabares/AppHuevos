package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.clienteDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Cliente
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels.ClientesViewModel
import com.nixoncortes1005.apphuevos2.ui.theme.BotonDetalles
import com.nixoncortes1005.apphuevos2.ui.theme.CardClientes
import com.nixoncortes1005.apphuevos2.ui.theme.ColorBordes
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ClientesCard(
    cliente: clienteDto,
    deuda: Int,
    onClick:(String) -> Unit,
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .border(1.5f.dp, ColorBordes, RoundedCornerShape(8.dp)),
       colors = CardDefaults.cardColors(CardClientes),
       shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = cliente.nombre,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = formatoMonedaColombiana(deuda),
                    style = MaterialTheme.typography.titleMedium,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Telefono: " + cliente.numTelefono,
                    color = Color.Black,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botón "Editar"
            Button(
                onClick = { onClick(cliente.idCliente.toString()) },
                modifier = Modifier
                    .wrapContentSize()
                    .height(42.dp),
                colors = ButtonDefaults.buttonColors(BotonDetalles)
            ) {
                Text(
                    text = "Detalles",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White,
                )
            }
        }
    }
}

fun formatoMonedaColombiana(valor: Int): String {
    val formato = NumberFormat.getCurrencyInstance(Locale("es", "CO"))
    formato.maximumFractionDigits = 0
    formato.minimumFractionDigits = 0
    return formato.format(valor)
}
