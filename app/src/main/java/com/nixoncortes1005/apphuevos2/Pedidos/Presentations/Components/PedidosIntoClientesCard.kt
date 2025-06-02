package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nixoncortes1005.apphuevos2.ui.theme.ColorBordes
import com.nixoncortes1005.apphuevos2.ui.theme.colorCardIntoPedidos

@Composable
fun PedidosIntoClientesCard(id: Long, fecha: String, onClick: () -> Unit = {}){

    Card(onClick = onClick,
        modifier = Modifier
        .fillMaxWidth()
        .border(1.5f.dp, ColorBordes, RoundedCornerShape(4.dp)),
        colors = CardDefaults.cardColors(colorCardIntoPedidos),
        elevation = CardDefaults.cardElevation(8.dp),){
        Row(Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Column(Modifier
                .padding(8.dp)
                .weight(2f)) {
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        text = fecha,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp
                    )
                }
                Spacer(Modifier.height(4.dp))
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        text = "Pedido # $id"
                    )
                }

            }
            Column(Modifier
                .weight(1f)
                .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "ir al pedido",
                    Modifier
                        .scale(2f)
                        .padding(12.dp)

                )
            }
        }
    }
}

@Preview
@Composable
fun previewwww(){
    PedidosIntoClientesCard(5,"11,11,2024")
}