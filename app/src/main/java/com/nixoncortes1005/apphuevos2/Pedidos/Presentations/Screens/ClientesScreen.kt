package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Screens

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.clienteDto
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components.ClientesCard
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels.ClientesViewModel
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels.PedidoUnitarioViewModel
import com.nixoncortes1005.apphuevos2.ui.theme.BotonAnadir
import com.nixoncortes1005.apphuevos2.ui.theme.ColorBordes

@Composable
fun ClientesScreen(
    navController: NavHostController,
    viewModel: ClientesViewModel = hiltViewModel()
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                "Clientes", modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleLarge
            )

            lista(navController, viewModel)
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
                onClick = { navController.navigate("AddClientesScreen") },
                modifier = Modifier
                    .border(0.5.dp, ColorBordes)
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
private fun lista(navController: NavHostController, viewModel: ClientesViewModel) {
    val clientescondeuda by viewModel.clientescondeuda.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getClientesConDeuda()
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 65.dp)
    ) {
        items(clientescondeuda.sortedByDescending { it.deuda }) { cliente ->
            ClientesCard(
                cliente,
                onClick = { navController.navigate("IntoClienteScreen/${cliente.idCliente}") },
                deuda = cliente.deuda,
            )
        }

    }
}

/*
* */
