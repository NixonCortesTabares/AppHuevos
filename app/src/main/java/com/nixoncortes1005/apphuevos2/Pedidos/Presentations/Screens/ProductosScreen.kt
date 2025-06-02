package com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Producto
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components.BotonAgregarProducto
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components.PedidosCard
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components.formatoMonedaColombiana
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels.ClientesViewModel
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels.ProductosViewModel

@Composable
fun ProductosScreen(navController: NavHostController, viewModel: ProductosViewModel = hiltViewModel()){

    Column(Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        infoHeader()
        Spacer(Modifier.height(8.dp))
        ListaProductos(navController, viewModel)


    }
}

@Composable
private fun infoHeader(){
    Text(
        text = "Productos",
        style = MaterialTheme.typography.titleLarge
    )
    Spacer(Modifier.height(8.dp))
    Row (Modifier.fillMaxWidth()){
        Text(
            text = "Aqui usted puede agregar los productos que ofrece.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
    Spacer(Modifier.height(12.dp))
    Row(Modifier.fillMaxWidth()) {
        Text(
            text = "Por ahora estos son los productos que ofrece:",
            style = MaterialTheme.typography.bodyLarge
        )
    }
    Spacer(Modifier.height(8.dp))
    Row(Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){
        Text(text = "Producto",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 12.sp)
        Text(text = "Precio",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 12.sp)
        Text(text = "Borrar",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 12.sp)

    }
}

@Composable
private fun ListaProductos(navController: NavHostController, viewModel: ProductosViewModel){

    val listaproductos by viewModel.productos.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getProductos()
    }

    LazyColumn(Modifier
        .fillMaxWidth()) {
        items(listaproductos){
                producto ->
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                Text(text = producto.nombreProducto)
                Text(text = formatoMonedaColombiana(producto.precioProducto))

                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "eliminar producto",
                    tint = Color.Red,
                    modifier = Modifier.clickable { viewModel.deleteProduct(producto)}
                )
            }
        }
    }

    BotonAgregarProducto(
        onClick = { navController.navigate("AddProductsScreen") },
        nombreBoton = "Agregar Producto",
    )

}