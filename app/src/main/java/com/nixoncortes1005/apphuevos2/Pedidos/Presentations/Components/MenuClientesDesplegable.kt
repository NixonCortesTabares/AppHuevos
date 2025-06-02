package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Cliente
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels.ClientesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuClientesDesplegable(
    clientes: List<Cliente>, // Lista de clientes obtenida desde Room
    onClienteSeleccionado: (Cliente) -> Unit // Callback que devuelve el objeto completo
) {
    var expanded by remember { mutableStateOf(false) } // Controla la apertura del menú
    var selectedCliente by remember { mutableStateOf<Cliente?>(null) } // Guarda el cliente seleccionado

    val selectedClienteNombre = selectedCliente?.nombre ?: "Seleccionar Cliente" // Muestra el nombre

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },

    ) {
        TextField(
            value = selectedClienteNombre, // Muestra el nombre del cliente seleccionado
            onValueChange = {},
            readOnly = true, // No se puede escribir manualmente
            label = { Text("Cliente") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false } // Cierra el menú al hacer clic afuera
        ) {
            clientes.forEach { cliente ->
                DropdownMenuItem(
                    text = { Text(cliente.nombre) }, // Muestra el nombre del cliente
                    onClick = {
                        selectedCliente = cliente // Guarda el objeto completo
                        expanded = false // Cierra el menú
                        onClienteSeleccionado(cliente) // Devuelve el objeto completo a la pantalla
                    }
                )
            }
        }
    }
}

