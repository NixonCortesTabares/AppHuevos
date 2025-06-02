package com.nixoncortes1005.apphuevos2.Control_insumos.Presentations.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nixoncortes1005.apphuevos2.R
import com.nixoncortes1005.apphuevos2.ui.theme.BotonDetalles
import com.nixoncortes1005.apphuevos2.ui.theme.CardsPedidos
import com.nixoncortes1005.apphuevos2.ui.theme.ColorBordes
import com.nixoncortes1005.apphuevos2.ui.theme.colorCardInsumos

@Composable
fun InsumosMensualesCard(headerCard:String, numControl: Long, fecha: String, color: Color, onClick:(Long) -> Unit){

    Card( elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.5f.dp, ColorBordes, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(color)) {


        Box(Modifier
            .fillMaxSize()
            .padding(16.dp)){
            Column(
                Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                Image(
                    painter = painterResource(R.drawable.caja),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 8.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "$headerCard # $numControl",
                    style = MaterialTheme.typography.titleMedium

                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Fecha de expedición: $fecha",
                )
                Spacer(modifier = Modifier.height(8.dp))
                Card(onClick = {onClick(numControl)},
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(ColorBordes),
                    modifier = Modifier
                        .fillMaxWidth(0.30f)
                        .height(48.dp) ){
                    Column(Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                        ){
                        Text(
                            text = "Detalles",
                            color = Color.White,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }

                }
            }
        }
    }

}