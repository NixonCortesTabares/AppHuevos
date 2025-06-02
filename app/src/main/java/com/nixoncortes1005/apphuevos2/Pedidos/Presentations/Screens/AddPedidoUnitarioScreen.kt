package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Screens

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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Cliente
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Producto
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.Relations.Producto_PedidoUnitario
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components.BotonAgregarProducto
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components.MenuClientesDesplegable
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components.MenuProductos
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels.ClientesViewModel
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels.PedidoSemanalViewModel
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels.PedidoUnitarioViewModel
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels.ProductosViewModel
import com.nixoncortes1005.apphuevos2.ui.theme.BotonAnadir
import com.nixoncortes1005.apphuevos2.ui.theme.ColorBordes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AddPedidoUnitarioScreen(
    navController: NavHostController,
    pedidoSemanalId: Long,
    viewModel: PedidoUnitarioViewModel = hiltViewModel(),
    viewModelPSem: PedidoSemanalViewModel = hiltViewModel(),
    viewModelCliente: ClientesViewModel = hiltViewModel(),
    viewModelProductos: ProductosViewModel = hiltViewModel(),
) {
    val clientes by viewModelCliente.clientes.collectAsStateWithLifecycle()
    var clienteSeleccionado by remember { mutableStateOf<Cliente?>(null) }
    val productos by viewModelProductos.productos.collectAsStateWithLifecycle()
    var productoSeleccionado by remember { mutableStateOf(setOf<Producto>()) }
    var cantidades by remember { mutableStateOf<Map<Long, String>>(mutableMapOf()) }
    var totalPedido by remember { mutableStateOf<Int>(0) }
    var cliqueable by remember { mutableStateOf<Boolean>(true) }

    LaunchedEffect(pedidoSemanalId) {
        viewModelPSem.getPedidoSemanalbyId(pedidoSemanalId)
        viewModelProductos.getProductos()
    }
    LaunchedEffect(clienteSeleccionado) {
        clienteSeleccionado?.let { viewModel.getPedidosCliente(it.idCliente) }
    }

    LaunchedEffect(cliqueable) {
        if(!cliqueable){
            delay(3500)
            cliqueable = true
        }
    }

    Box(Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Column(
            Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Añadir Pedido", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))
            Text("Selecciona el cliente al cual se le va a anotar el pedido.")
            Spacer(Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                MenuClientesDesplegable(clientes) { cliente ->
                    clienteSeleccionado = cliente
                }
            }
            Text("Seleccionar productos", style = MaterialTheme.typography.titleMedium  )
            Spacer(Modifier.height(8.dp))

            LazyColumn(Modifier.fillMaxWidth()) {
                items(productos) { producto ->
                    MenuProductos(
                        producto = producto,
                        isSelected = producto in productoSeleccionado,
                        cantidad = cantidades[producto.idProducto] ?: "",
                        onSelectionChange = { selected ->
                            productoSeleccionado = if (selected) {
                                productoSeleccionado + producto
                            } else {
                                productoSeleccionado - producto
                            }
                        },
                        onCantidadChange = { nuevaCantidad ->
                            cantidades = cantidades.toMutableMap().apply { // 🔹 Crea una nueva instancia del mapa
                                this[producto.idProducto] = nuevaCantidad
                            }
                        }
                    )
                }
            }

            Text(
                text = "Total del pedido: $totalPedido",
                style = MaterialTheme.typography.titleMedium
            )
            BotonAgregarProducto(
                onClick = {
                    CoroutineScope(Dispatchers.Default).launch {
                        totalPedido = 0
                        for (producto in productoSeleccionado) {
                            for (cantidad in cantidades) {
                                if (cantidad.key == producto.idProducto) {
                                    totalPedido += (producto.precioProducto * cantidad.value.toFloat()).toInt()
                                }
                            }
                        }
                        clienteSeleccionado?.let {
                            if (cliqueable) {
                                cliqueable = false

                                withContext(Dispatchers.IO){
                                    viewModel.insertarOActualizarPedidoUnitario(
                                        pedidoSemanalId = pedidoSemanalId,
                                        totalAnterior = totalPedido,
                                        cantidades = cantidades,
                                        cliente = clienteSeleccionado!!
                                    )
                                }

                                withContext(Dispatchers.Main){
                                    navController.popBackStack()
                                }

                            }
                        }
                    }


                },
                nombreBoton = "Añadir Pedido",
            )

        }

        Column(Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally) {
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

