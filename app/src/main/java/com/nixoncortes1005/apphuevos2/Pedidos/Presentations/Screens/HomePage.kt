package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil3.compose.rememberAsyncImagePainter
import com.nixoncortes1005.apphuevos2.Balance.Presentations.viewModels.BalanceViewModel
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components.CardGrandeActivity
import com.nixoncortes1005.apphuevos2.R

@Composable
fun HomePage(navController: NavHostController){

    Box(Modifier
        .fillMaxSize()
        .padding(16.dp)){

        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            Text("Gestión Parcela",
                style = MaterialTheme.typography.titleLarge,
            )
            Image(
                painter = rememberAsyncImagePainter(R.drawable.farm_image),
                contentDescription = null,
            )

            LazyColumn(Modifier.fillMaxWidth()) {
                item{
                    CardGrandeActivity(
                        "Clientes" ,
                        painterResource(R.drawable.user),
                        onClick = {navController.navigate("ClientesScreen")}

                    )
                }

                item {
                    CardGrandeActivity(
                        "Control de Pedidos",
                        painterResource(R.drawable.maximize),
                        onClick = {navController.navigate("PedidosScreen")}
                    )
                }

                item {
                    CardGrandeActivity(
                        "Control de insumos",
                        painterResource(R.drawable.caja),
                        onClick = {navController.navigate("ControlInsumosScreen")}
                    )
                }

                item {
                    CardGrandeActivity(
                        "Balance de Ventas",
                        painterResource(R.drawable.tabla),
                        onClick = {navController.navigate("BalancesScreen")}
                    )
                }

                item {
                    CardGrandeActivity(
                        "Productos",
                        painterResource(R.drawable.shoppingcart),
                        onClick = {navController.navigate("ProductosScreen")}
                    )
                }

            }


        }
    }
}


