package com.nixoncortes1005.apphuevos2.Control_insumos.Presentations.Screens

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
import com.nixoncortes1005.apphuevos2.Control_insumos.Presentations.Components.InsumosMensualesCard
import com.nixoncortes1005.apphuevos2.Control_insumos.Presentations.viewModels.InsumosViewModel
import com.nixoncortes1005.apphuevos2.ui.theme.BotonAnadir
import com.nixoncortes1005.apphuevos2.ui.theme.ColorBordes
import com.nixoncortes1005.apphuevos2.ui.theme.colorCardInsumos

@Composable
fun ControlInsumosScreen(navController: NavHostController, viewModel: InsumosViewModel = hiltViewModel()){

    Box(Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Column( modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top){
            Text("Insumos",
                style = MaterialTheme.typography.titleLarge)

            lista(navController, viewModel)

        }

        Row(Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .padding(4.dp),
            horizontalArrangement = Arrangement.Center) {

            FloatingActionButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .border(0.5.dp, ColorBordes)
                    .fillMaxWidth(0.3f),
                shape = RectangleShape,
                containerColor = BotonAnadir
            ) {
                Text("Atrás")
            }
        }

    }

}

@Composable
private fun lista(navController: NavHostController, viewModel: InsumosViewModel){

    val listaInsumosMensual by viewModel.controlInsumos.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getListInsumosMensuales()
    }


    LazyColumn(Modifier
        .fillMaxWidth()
        .padding(bottom = 60.dp)) {
        items(listaInsumosMensual){
                insumoMensual ->
            InsumosMensualesCard(
                numControl = insumoMensual.idControlInsumos,
                fecha = insumoMensual.fecha,
                onClick = { navController.navigate("IntoInsumosScreen/${insumoMensual.idControlInsumos}") },
                color = colorCardInsumos,
                headerCard = "Insumo Mensual"
            )

        }
    }

}