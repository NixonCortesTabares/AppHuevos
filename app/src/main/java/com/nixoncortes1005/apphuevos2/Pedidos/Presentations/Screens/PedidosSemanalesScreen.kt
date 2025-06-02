package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoSemanal
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components.PedidosCard
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels.PedidoSemanalViewModel
import com.nixoncortes1005.apphuevos2.ui.theme.BotonAnadir
import com.nixoncortes1005.apphuevos2.ui.theme.ColorBordes

@Composable
fun PedidosScreen(
    navController: NavHostController,
    viewModel: PedidoSemanalViewModel = hiltViewModel()
) {

    Box(Modifier
        .fillMaxSize()
        .padding(16.dp)){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Pedidos",
                style = MaterialTheme.typography.titleLarge
            )
            ///AQ
            listaaa(navController, viewModel)

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(4.dp)
            ,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            FloatingActionButton(
                onClick = { viewModel.insertPedidoSemanal() },
                modifier = Modifier
                    .border(0.5f.dp, ColorBordes)
                    .fillMaxWidth(0.25f),
                shape = RectangleShape,
                containerColor = BotonAnadir
            ) {
                Text("Añadir")
            }


            FloatingActionButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .border(0.5.dp, ColorBordes)
                    .fillMaxWidth(0.25f),
                shape = RectangleShape,
                containerColor = BotonAnadir
            ) {
                Text("Atrás")
            }
        }
    }



}


@Composable
private fun listaaa(navController: NavHostController, viewModel: PedidoSemanalViewModel) {

    val pedidos by viewModel.pedidos.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getPedidos()
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 65.dp)
    ) {
        items(pedidos.reversed()) { pedido ->
            PedidosCard(
                pedido,
                onClick = { navController.navigate("IntoPedidosScreen/${pedido.idPedidoSemanal}") })
        }
    }
}
