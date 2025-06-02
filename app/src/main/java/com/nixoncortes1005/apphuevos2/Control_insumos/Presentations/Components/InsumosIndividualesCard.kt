package com.nixoncortes1005.apphuevos2.Control_insumos.Presentations.Components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components.formatoMonedaColombiana
import com.nixoncortes1005.apphuevos2.ui.theme.ColorBordes
import com.nixoncortes1005.apphuevos2.ui.theme.ColorCardInsumosIndividuales

@Composable
fun InsumosIndividualesCard( nombreInsumo: String, precioInsumo: Int, fecha: String, onClick:() -> Unit, onClick2: () -> Unit){

    Row (Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top){

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.5f.dp, ColorBordes, RoundedCornerShape(8.dp)),
            onClick = {onClick()},
            colors = CardDefaults.cardColors(ColorCardInsumosIndividuales)
            ) {
            Column(Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                Box(){
                    IconButton(onClick = {onClick2()},
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .size(28.dp),
                        colors = IconButtonDefaults.iconButtonColors(Color.Black),
                    ) {

                        Icon(Icons.Default.Clear, contentDescription = "eliminar", tint = Color.White)
                    }
                }
                Spacer(Modifier.height(4.dp))
                Row(Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Text( text = nombreInsumo,
                        style = MaterialTheme.typography.titleMedium)
                    Text( text = formatoMonedaColombiana(precioInsumo),
                        style = MaterialTheme.typography.titleLarge)
                }
                Spacer(Modifier.height(4.dp))
                Box(Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .align(Alignment.Start)){
                    Text("Fecha de expedición: $fecha")
                }
            }
        }
    }
}

@Preview
@Composable
private fun preview(){
    InsumosIndividualesCard(
        nombreInsumo = "Prueba",
        precioInsumo = 2500,
        fecha = "25/05/2024",
        onClick = {},
        onClick2 = {}
    )
}
