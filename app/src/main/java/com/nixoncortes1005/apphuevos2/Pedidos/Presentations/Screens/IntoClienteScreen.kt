package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Screens

import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.nixoncortes1005.apphuevos2.Pedidos.Data.Entity.PedidoUnitario_PagosEntity
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.PedidoUnitarioDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.DTO.clienteDto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Cliente
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Pagos
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.PedidoUnitario
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components.EditarClienteDialog
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components.PedidosIntoClientesCard
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components.formatoMonedaColombiana
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels.ClientesViewModel
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels.PagosViewModel
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels.PedidoUnitarioViewModel
import com.nixoncortes1005.apphuevos2.ui.theme.BotonAnadir
import com.nixoncortes1005.apphuevos2.ui.theme.ColorBordes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun IntoClienteScreen(navController: NavHostController, clienteId: Long, viewModel: ClientesViewModel = hiltViewModel(), viewModelPagos: PagosViewModel = hiltViewModel(), viewModelPU: PedidoUnitarioViewModel = hiltViewModel()){

    var abono by remember { mutableStateOf<String>("") }
    var cliqueable by remember { mutableStateOf<Boolean>(true)}

    LaunchedEffect(cliqueable) {
        if(!cliqueable){
            delay(3000)
            cliqueable = true
        }
    }

       Column(
        Modifier
            .fillMaxSize()
            .padding(top = 12.dp, start = 24.dp,
                end = 24.dp, bottom = 8.dp)
       ){
           CardCliente(clienteId,viewModel)

           Spacer(Modifier.height(16.dp))

           Column(Modifier
               .fillMaxWidth()
               .border( width = 0.5f.dp, // Grosor del borde
                   color = Color.Black, // Color del borde
                   shape = RoundedCornerShape(8.dp))) {
               TextField(
                   value = abono,
                   onValueChange = {
                       nuevoPrecio ->
                       if (nuevoPrecio.all { it.isDigit() }){
                           abono = nuevoPrecio
                       }
                   },
                   label = { Text("Introduzca la cantidad que el cliente abonó") },
                   modifier = Modifier.fillMaxWidth()
               ) }
               Spacer(Modifier.height(4.dp))
           Column(Modifier
               .fillMaxWidth()
               .padding(16.dp)) {
               Button(onClick = {
                   cliqueable = false
                   var abonoInt: Int = 0
                   if(abono != ""){
                       abonoInt = abono.toInt()
                   }
                   if(abonoInt > 0){
                       viewModel.calcularCostoPedidoNuevo(abonoInt)
                       viewModel.actualizarClienteMostrado(abonoInt)
                       abono = ""
                   }
               },
                   modifier = Modifier.fillMaxWidth(),
                   enabled = cliqueable,
                   elevation = ButtonDefaults.buttonElevation(8.dp),
                   colors = ButtonDefaults.buttonColors(Color.Green)) {
                   Text(text = "Realizar Abono",
                       style = MaterialTheme.typography.bodyLarge,
                       fontSize = 16.sp)
               }

           }

             HeaderLista()
           Spacer(Modifier.height(4.dp))

           lista(clienteId, navController, viewModel)

           Row(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(bottom = 16.dp),
               verticalAlignment = Alignment.Bottom,
               horizontalArrangement = Arrangement.Center
           ) {

               FloatingActionButton(
                   onClick = { navController.popBackStack() },
                   modifier = Modifier
                       .border(0.5f.dp, ColorBordes),
                   shape = RectangleShape,
                   containerColor = BotonAnadir
               ) {
                   Text(
                       text = "Atrás",
                       modifier = Modifier.padding(8.dp)
                   )
               }
           }

           }
       }

@Composable
private fun CardCliente(clienteId: Long, viewModel: ClientesViewModel){

    var clienteguardado by viewModel.clienteguardado
    var mostrarDialogo by remember { mutableStateOf(false) }

   LaunchedEffect(Unit) {
        viewModel.getClienteAMostrar(clienteId)
    }

    Row (Modifier
        .fillMaxWidth(1f),
        horizontalArrangement = Arrangement.SpaceBetween){
        Text(
            text = "Perfil",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        IconButton(
            onClick = {mostrarDialogo = true},
            colors = IconButtonDefaults.iconButtonColors(Color.White)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Editar",
                tint = Color.Black,
                modifier = Modifier.size(30.dp)
            )
        }

    }

    Spacer(Modifier.height(8.dp))

    Box(Modifier
        .fillMaxWidth()){
        Card(modifier = Modifier
            .fillMaxWidth()
            ,
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(Color.White)) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier
                        .size(54.dp),
                    tint = Color.Black
                )


                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = clienteguardado.nombre,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 20.sp
                    )
                    Text(
                        text = formatoMonedaColombiana(clienteguardado.deuda),
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 18.sp
                    )
                    Text(
                        text = clienteguardado.numTelefono,
                        style = MaterialTheme.typography.bodyLarge
                    )

                }
            }
        }
        if (mostrarDialogo) {
                EditarClienteDialog(
                    cliente = clienteguardado,
                    onDismiss = { mostrarDialogo = false },
                    onGuardar = { nuevoNombre, nuevoTelefono ->
                        viewModel.actualizarDatosCliente(clienteId,nuevoNombre, nuevoTelefono)
                        viewModel.actualizarNombreYNumClienteMostrado(nuevoNombre, nuevoTelefono)
                        mostrarDialogo = false

                    }
                )
            }

        }
    }


@Composable
private fun HeaderLista(){
    Column(Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Text(text = "Lista de Pedidos",
            style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
private fun lista(clienteId: Long, navController: NavHostController, viewModel: ClientesViewModel){
    val pedidosUnitarios by viewModel.clientepedidoinfo.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getpedidosCliente(clienteId)
    }
    LazyColumn(Modifier
        .fillMaxWidth()
        .padding(bottom = 100.dp)) {
        items(pedidosUnitarios.reversed()){
                pedido ->
            Row(Modifier
                .fillMaxWidth()) {
                PedidosIntoClientesCard(pedido.idPedidoUnitario,pedido.fecha, onClick = {navController.navigate("IntoPedidosScreen/${pedido.pedidoSemanalId}")})
            }
        }
    }
}


