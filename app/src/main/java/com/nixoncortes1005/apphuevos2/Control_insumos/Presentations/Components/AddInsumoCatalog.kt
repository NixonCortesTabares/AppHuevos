package com.nixoncortes1005.apphuevos2.Control_insumos.Presentations.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nixoncortes1005.apphuevos2.Control_insumos.Domain.Models.Insumos
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.clienteDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Cliente
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun AddInsumoCatalog(
    onDismiss: () -> Unit,
    onGuardar: (String, String) -> Unit,
    titulo:String
){
    var nombre by remember { mutableStateOf("") }
    var costo by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(titulo) },
        text = {
            Column {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = costo,
                    onValueChange = { costo = it },
                    label = { Text("Costo Insumo") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )

            }
        },
        confirmButton = {
            Button(onClick = { onGuardar(nombre, costo ) }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )

}

fun getFechaHoy(): String{
    val hoy = LocalDate.now()
    val formato = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    return hoy.format(formato)
}