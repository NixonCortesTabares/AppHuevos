package com.nixoncortes1005.apphuevos2.Balance.Presentations.Screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.nixoncortes1005.apphuevos2.Balance.Domain.Models.BalanceVentas
import com.nixoncortes1005.apphuevos2.Balance.Presentations.viewModels.BalanceViewModel
import com.nixoncortes1005.apphuevos2.Control_insumos.Presentations.Components.InsumosMensualesCard
import com.nixoncortes1005.apphuevos2.ui.theme.BotonAnadir
import com.nixoncortes1005.apphuevos2.ui.theme.ColorBordes


@Composable
fun BalancesScreen(navController: NavHostController, viewModel: BalanceViewModel = hiltViewModel()){

    Box(Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Column(Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Balances de venta",
                style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))
            listaBalances(navController, viewModel)

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


@Composable
private fun listaBalances(navController: NavHostController, viewModel: BalanceViewModel){

    val listaBalances by viewModel.balanceVentas.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getBalances()
    }

    LazyColumn(Modifier
        .fillMaxWidth()
        .padding(bottom = 65.dp)) {
        items(listaBalances){
            Balance ->
            InsumosMensualesCard(
                numControl = Balance.idBalanceVentas,
                fecha = Balance.mesCreacion,
                color = Color.Green,
                onClick = {navController.navigate("IntoBalancesScreen/${Balance.idBalanceVentas}")},
                headerCard = "Balance mensual"
            )

        }
    }


}

