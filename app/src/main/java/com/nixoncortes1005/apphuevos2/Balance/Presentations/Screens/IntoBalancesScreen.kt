package com.nixoncortes1005.apphuevos2.Balance.Presentations.Screens

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.nixoncortes1005.apphuevos2.Balance.Presentations.viewModels.BalanceViewModel
import com.nixoncortes1005.apphuevos2.Control_insumos.Presentations.viewModels.InsumosViewModel
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoSemanal
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components.PedidosCard
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components.formatoMonedaColombiana
import com.nixoncortes1005.apphuevos2.ui.theme.BotonAnadir
import com.nixoncortes1005.apphuevos2.ui.theme.ColorBordes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun IntoBalancesScreen(navController: NavHostController, balanceid: Long, viewModel: BalanceViewModel = hiltViewModel(), viewModelInsumos: InsumosViewModel = hiltViewModel()){
    val listaPedidos by viewModel.pedidosperbalance.collectAsStateWithLifecycle()
    val detBalance by viewModel.detbalance.collectAsStateWithLifecycle()
    val insumosUnitarios by viewModelInsumos.insumosUnitariosbyId.collectAsStateWithLifecycle()
    var GananciasEnCaja by remember { mutableStateOf(0) }
    var cantidadEnDeuda by remember { mutableStateOf(0) }
    var cantidadInsumos by remember { mutableStateOf(0) }
    var totalGanancias by remember { mutableStateOf(0) }

    LaunchedEffect(balanceid) {
        viewModel.getBalanceById(balanceid)
        viewModel.getPedidosDetBalance(balanceid)
        viewModelInsumos.getInsumosUnitariosbyId(balanceid)
    }

    LaunchedEffect(Unit) {
        GananciasEnCaja = listaPedidos.sumOf { it.totalPedidoSemanal }
        cantidadEnDeuda = listaPedidos.sumOf { it.totalEnDeuda }
        cantidadInsumos = insumosUnitarios.sumOf { it.CostoInsumo }
        totalGanancias = GananciasEnCaja + cantidadEnDeuda - cantidadInsumos
    }

    Log.e("Esta entrando por el launched", totalGanancias.toString())

    Box(Modifier
        .fillMaxSize()
        .padding(16.dp)){

        Column(
            Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Balance mensual # ${detBalance.idBalanceVentas}",
                style = MaterialTheme.typography.titleMedium)
            Text(text = detBalance.mesCreacion,
                style = MaterialTheme.typography.titleMedium)

            Column(Modifier.fillMaxWidth()) {
                Text(text = "Las ganancias netas este mes fueron: " + formatoMonedaColombiana(totalGanancias),
                    style = MaterialTheme.typography.labelLarge)
                Text(text = "De las ventas, se encuentran en deuda: " + formatoMonedaColombiana(cantidadEnDeuda))
                Text(text = "y hubo costos de: " + formatoMonedaColombiana(cantidadInsumos))
                Text(text = "Y tenemos un total de ventas en caja de: " + formatoMonedaColombiana(GananciasEnCaja))
            }

            LazyColumn(Modifier
                .fillMaxWidth()
                .padding(bottom = 65.dp)) {

                items(listaPedidos){
                    PedidoSemanal ->
                    PedidosCard(pedido = PedidoSemanal,
                        onClick = {navController.navigate("IntoPedidosScreen/${PedidoSemanal.idPedidoSemanal}")})

                }

            }

        }

        Row(Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center) {

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
