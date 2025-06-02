package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Producto
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components.BotonAgregarProducto
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels.ProductosViewModel
import kotlinx.coroutines.delay

@Composable
fun AddProductsScreen(navController: NavHostController, viewModel: ProductosViewModel = hiltViewModel()){

    var nombre by remember{ mutableStateOf("")}
    var precio by remember { mutableStateOf("")}
    var cliqueable by remember { mutableStateOf<Boolean>(true) }

    LaunchedEffect(cliqueable) {
        if(!cliqueable){
            delay(3000)
            cliqueable = true
        }
    }

    Column(Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Agregar Producto",
            style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(16.dp))

        Row(Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            TextField(
                value = nombre,
                onValueChange = {nuevoTexto ->
                    nombre = nuevoTexto
                },
                label = { Text("Nombre del Producto") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(Modifier.height(16.dp))

        Row(Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
           TextField(
               value = precio,
               onValueChange = {nuevoPrecio ->
                   if (nuevoPrecio.all { it.isDigit() }){
                       precio = nuevoPrecio
                   }

               },
               label = { Text("Precio Unitario del producto") },
               modifier = Modifier.fillMaxWidth()
           )
        }

            Spacer(Modifier.height(32.dp))

        Row(Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
            ) {

            BotonAgregarProducto(
                onClick = {
                    val producto = Producto(
                        idProducto = 0,
                        nombreProducto = nombre,
                        precioProducto = precio.toInt()
                    )
                    viewModel.addProduct(producto)
                    navController.popBackStack()
                },
                nombreBoton = "Agregar Producto",
            )
        }

        }
    }

