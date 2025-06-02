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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Models.ModelsPrimitives.Cliente
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.ViewModels.ClientesViewModel
import com.nixoncortes1005.apphuevos2.ui.theme.BotonAnadir
import com.nixoncortes1005.apphuevos2.ui.theme.ColorBordes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AddClientesScreen(navController: NavHostController, viewModel: ClientesViewModel = hiltViewModel()) {
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Agregar Cliente",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(8.dp))
            textoField(nombre, "Nombre", "Ingrese el nombre del cliente", onValueChange = { newName ->
                if (newName.all { it.isLetter() || it.isWhitespace() }) {
                    nombre = newName
                }
            })
            Spacer(Modifier.height(8.dp))
            textoField(
                telefono,
                "Telefono",
                "Ingrese el numero telefonico del cliente",
                onValueChange = { newTel ->
                    if (newTel.all { it.isDigit() }) {
                        telefono = newTel
                    }
                })
            Spacer(Modifier.height(32.dp))

            botones(nombre, telefono, navController, viewModel)

        }

    }


@Composable
fun textoField(value: String, campo: String, descripcion: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(campo) },
        placeholder = { Text(descripcion) },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        singleLine = true
    )
}

@Composable
private fun botones(nombre: String, telefono:String, navController: NavHostController, viewModel: ClientesViewModel){

    Row(Modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically){
        Card(onClick = {
            if(nombre != ""){
                    viewModel.insertClientes(
                        Cliente(
                            idCliente = 0,
                            nombre = nombre,
                            numTelefono = telefono
                        )
                    )
                    navController.popBackStack()

            }

        },
            modifier = Modifier
                .border(1.0f.dp, ColorBordes, RoundedCornerShape(8.dp))
                .weight(1f)
                .height(48.dp),
            colors = CardDefaults.cardColors(BotonAnadir)
        ) {
            Box(Modifier
                .fillMaxSize()
                .padding(4.dp)){
                Text(
                    text = "Añadir",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .align(Alignment.Center)

                )
            }

        }
        Card(onClick = {
            navController.popBackStack()
        },
            modifier = Modifier
                .border(1.0f.dp, ColorBordes, RoundedCornerShape(8.dp))
                .weight(1f)
                .height(48.dp),
            colors = CardDefaults.cardColors(BotonAnadir)
        ) {
            Box(Modifier
                .fillMaxSize()
                .padding(4.dp)){
                Text(
                    text = "Atras",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

        }
    }

}