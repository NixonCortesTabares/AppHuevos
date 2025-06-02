package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Screens

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components.PedidoUnitarioCard
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels.ClientesViewModel
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels.PedidoSemanalViewModel
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels.PedidoUnitarioViewModel
import com.nixoncortes1005.apphuevos2.ui.theme.BotonAnadir
import com.nixoncortes1005.apphuevos2.ui.theme.ColorBordes

@Composable
fun IntoPedidosScreen(
    navController: NavHostController,
    pedidoSemanalId: Long,
    viewModel: PedidoSemanalViewModel = hiltViewModel(),
    viewModelPU: PedidoUnitarioViewModel = hiltViewModel()
) {

    val pedidoCorrespondiente by viewModel.pedidoSemanalSelected.collectAsStateWithLifecycle()
    val pedidosConNombre by viewModelPU.pedidoswithnombre.collectAsStateWithLifecycle()
    val listaCantidades by viewModelPU.listacantidades.collectAsStateWithLifecycle()
    val listaProductos by viewModelPU.listaproductos.collectAsStateWithLifecycle()

    LaunchedEffect(pedidoSemanalId) {
        viewModel.getPedidosUnitariosDetSemana(pedidoSemanalId)
        viewModel.getPedidoSemanalbyId(pedidoSemanalId)
        viewModelPU.getPedidosWithNombre(pedidoSemanalId)
        viewModelPU.getCantidadesyProductos(pedidoSemanalId)

    }
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){    Column(
        Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pedido # $pedidoSemanalId",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = pedidoCorrespondiente.fechaCreacion,
            style = MaterialTheme.typography.titleMedium
        )

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 64.dp)
        ) {
            items(pedidosConNombre) { pedido ->
                val listaaProductos =
                    listaProductos[pedido.idCliente]?.map { it.nombreProducto } ?: emptyList()
                val listaaCantidades =
                    listaCantidades[pedido.idCliente]?.map { it.cantidadProducto } ?: emptyList()
                PedidoUnitarioCard(
                    nombreCliente = pedido.nombre,
                    pagoGenerado = pedido.totalPedido,
                    listaProductos = listaaProductos,
                    cantidades = listaaCantidades
                )
            }
        }
    }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FloatingActionButton(
                onClick = { navController.navigate("AddPedidoUnitarioScreen/$pedidoSemanalId") },
                modifier = Modifier
                    .border(0.5f.dp, ColorBordes)
                    .fillMaxWidth(0.25f),
                shape = RectangleShape,
                containerColor = BotonAnadir
            ) {
                Text(
                    text = "Añadir",
                )
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