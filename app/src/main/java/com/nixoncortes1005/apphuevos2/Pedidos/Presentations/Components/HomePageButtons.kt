package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components

import android.service.autofill.OnClickAction
import android.util.Log
import android.view.View.OnClickListener
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.VectorProperty
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import arrow.core.left
import coil3.compose.rememberAsyncImagePainter
import com.nixoncortes1005.apphuevos2.R
import com.nixoncortes1005.apphuevos2.ui.theme.BotonesPrincipales
import com.nixoncortes1005.apphuevos2.ui.theme.ColorBordes


@Composable
fun CardGrandeActivity(
    TextoCard: String,
    imagen: Painter,
    onClick:() -> Unit
) {

    Card(
        onClick = { onClick()},
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(16.dp)
            .height(48.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = BotonesPrincipales)

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
        ) {
            Image(
                painter = imagen,
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterStart)
                    .size(16.dp),
                contentScale = ContentScale.Fit

            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                TextoCard,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .align(Alignment.Center)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Image(
                painter = painterResource(R.drawable.more_horizontal),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterEnd)
                    .size(16.dp),
                contentScale = ContentScale.Fit

                )
        }

    }
}

