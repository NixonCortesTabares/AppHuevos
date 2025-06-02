package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BotonAgregarProducto(
    onClick: () -> Unit = {},
    nombreBoton: String,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp, // Elevación decente
            pressedElevation = 3.dp,
            hoveredElevation = 8.dp
        ),
        modifier = Modifier.padding(8.dp),
    ) {
        Text(
            text = nombreBoton,
            style = MaterialTheme.typography.labelLarge
        )
    }
}
