package com.nixoncortes1005.apphuevos2.Control_insumos.Presentations.Screens

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.nixoncortes1005.apphuevos2.Control_insumos.Domain.Models.Insumos
import com.nixoncortes1005.apphuevos2.Control_insumos.Presentations.Components.AddInsumoCatalog
import com.nixoncortes1005.apphuevos2.Control_insumos.Presentations.Components.InsumosIndividualesCard
import com.nixoncortes1005.apphuevos2.Control_insumos.Presentations.Components.getFechaHoy
import com.nixoncortes1005.apphuevos2.Control_insumos.Presentations.viewModels.InsumosViewModel
import com.nixoncortes1005.apphuevos2.ui.theme.BotonAnadir
import com.nixoncortes1005.apphuevos2.ui.theme.ColorBordes
import kotlinx.coroutines.launch

@Composable
fun IntoInsumosScreen(
    navController: NavHostController,
    idControlInsumos: Long,
    viewModel: InsumosViewModel = hiltViewModel()
) {
    var activador by remember { mutableStateOf(false) }
    var checker by remember { mutableStateOf(false) }
    var insumoSeleccionado by remember { mutableStateOf<Insumos?>(null) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val insumosUnitarios by viewModel.insumosUnitariosbyId.collectAsStateWithLifecycle()

    LaunchedEffect(Unit, checker) {
        viewModel.getInsumosUnitariosbyId(idControlInsumos)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center,
            ) {
                FloatingActionButton(
                    onClick = { activador = true },
                    modifier = Modifier
                        .border(0.5.dp, ColorBordes, RoundedCornerShape(10.dp)),
                    shape = RoundedCornerShape(8.dp),
                    containerColor = BotonAnadir
                ) {
                    Text("Añadir Insumo")
                }
            }
        }

    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Insumos", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))

            LazyColumn(Modifier.fillMaxWidth()
                .padding()) {
                items(insumosUnitarios) { insumo ->
                    InsumosIndividualesCard(
                        nombreInsumo = insumo.NombreInsumo,
                        precioInsumo = insumo.CostoInsumo,
                        fecha = insumo.fecha,
                        onClick = { insumoSeleccionado = insumo },
                        onClick2 = {
                            viewModel.deleteInsumo(insumo)
                            checker = !checker
                        }
                    )
                    Spacer(Modifier.height(4.dp))
                }
            }
        }

        insumoSeleccionado?.let { insumo ->
            AddInsumoCatalog(
                onDismiss = { insumoSeleccionado = null },
                onGuardar = { nombre, costo ->
                    if (nombre.isNotBlank() && costo.isNotBlank()) {
                        viewModel.updateInsumo(
                            id = insumo.idInsumos,
                            nombreNuevo = nombre,
                            costoNuevo = costo.toInt()
                        )
                        insumoSeleccionado = null
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar("No dejes campos vacíos.")
                        }
                    }
                    checker = !checker
                },
                titulo = "Actualizar Insumo"
            )
        }

        if (activador) {
            AddInsumoCatalog(
                onDismiss = { activador = false },
                onGuardar = { nombre, costo ->
                    viewModel.insertInsumo(
                        insumo = Insumos(
                            idInsumos = 0,
                            NombreInsumo = nombre,
                            CostoInsumo = costo.toInt(),
                            controlInsumosId = idControlInsumos,
                            fecha = getFechaHoy()
                        )
                    )
                    activador = false
                    checker = !checker
                },
                titulo = "Añadir Insumo"
            )
        }
    }
}
