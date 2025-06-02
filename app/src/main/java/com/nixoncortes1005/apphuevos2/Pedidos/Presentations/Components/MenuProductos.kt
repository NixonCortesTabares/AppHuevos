package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Cliente
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Producto

@Composable
fun MenuProductos(
    producto: Producto,
    isSelected: Boolean,
    cantidad: String,
    onSelectionChange: (Boolean) -> Unit,
    onCantidadChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelectionChange(!isSelected) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = { onSelectionChange(it) },
            modifier = Modifier.weight(0.5f)
        )
        Text(
            text = producto.nombreProducto,
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = {
                val nuevaCantidad = (cantidad.toFloatOrNull() ?: 0f) + 1f
                onCantidadChange(nuevaCantidad.toString())
            },
            modifier = Modifier.weight(0.4f),
            colors = IconButtonDefaults.iconButtonColors(Color.Black)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "AgregarCantidad",
                tint = Color.White)
        }
        TextField(
            value = cantidad,
            onValueChange = { newCantidad ->
                if (newCantidad.toFloatOrNull() != null || newCantidad.isEmpty()) {
                    onCantidadChange(newCantidad)
                }
            },
            modifier = Modifier.weight(0.5f)
        )
        IconButton(
            onClick = {
                val nuevaCantidad = (cantidad.toFloatOrNull() ?: 0f) - 1f
                if (nuevaCantidad >= 0) {
                    onCantidadChange(nuevaCantidad.toString())
                }
            },
            modifier = Modifier.weight(0.4f),
            colors = IconButtonDefaults.iconButtonColors(Color.Black)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "ReducirCantidad",
                tint = Color.White
            )
        }
    }
}
