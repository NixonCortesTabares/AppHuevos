package com.nixoncortes1005.apphuevos2.di.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nixoncortes1005.apphuevos2.Balance.Presentations.Screens.BalancesScreen
import com.nixoncortes1005.apphuevos2.Balance.Presentations.Screens.IntoBalancesScreen
import com.nixoncortes1005.apphuevos2.Control_insumos.Presentations.Screens.ControlInsumosScreen
import com.nixoncortes1005.apphuevos2.Control_insumos.Presentations.Screens.IntoInsumosScreen
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Screens.AddClientesScreen
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Screens.AddPedidoUnitarioScreen
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Screens.AddProductsScreen
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Screens.ClientesScreen
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Screens.HomePage
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Screens.IntoClienteScreen
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Screens.IntoPedidosScreen
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Screens.PedidosScreen
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Screens.ProductosScreen

@Composable
fun ConfigNavController(navController: NavHostController){
NavHost(
    navController = navController,
    startDestination = "HomePage",
){
    composable("HomePage") {
        HomePage(navController) // Pantalla inicial
    }
    composable("PedidosScreen") {
        PedidosScreen(navController) // Pantalla de detalles
    }
    composable("IntoPedidosScreen/{pedidoSemanalId}",
        arguments = listOf(
            navArgument("pedidoSemanalId"){type = NavType.LongType}
        )){
        backStackEntry ->
        val pedidoSemanalId = backStackEntry.arguments?.getLong("pedidoSemanalId") ?: 0
        IntoPedidosScreen(navController, pedidoSemanalId)
    }
    composable("ClientesScreen") {
        ClientesScreen(navController)
    }
    composable("AddClientesScreen") {
        AddClientesScreen(navController)
    }
    composable("IntoClienteScreen/{clienteId}",
        arguments = listOf(
            navArgument("clienteId"){type = NavType.LongType}
        )
    ) {
        backStackEntry ->
        val clienteId = backStackEntry.arguments?.getLong("clienteId") ?: 0
        IntoClienteScreen(navController, clienteId)
    }
    composable("BalancesScreen") {
        BalancesScreen(navController)
    }
    composable("IntoBalancesScreen/{balanceid}",
        arguments = listOf(
            navArgument("balanceid"){type = NavType.LongType}
        )) {
        backStackEntry ->
        val balanceid = backStackEntry.arguments?.getLong("balanceid") ?: 0
        IntoBalancesScreen(navController, balanceid)
    }

    composable("ProductosScreen") {
            ProductosScreen(navController)
    }

    composable("AddProductsScreen") {
        AddProductsScreen(navController)
    }

    composable("AddPedidoUnitarioScreen/{pedidoSemanalId}",
        arguments = listOf(
            navArgument("pedidoSemanalId"){ type = NavType.LongType}
        )
    ) {
        backStackEntry ->
        val pedidoSemanalId = backStackEntry.arguments?.getLong("pedidoSemanalId")?: 0
        AddPedidoUnitarioScreen(navController, pedidoSemanalId)
    }

    composable("ControlInsumosScreen") {
        ControlInsumosScreen(navController)
    }

    composable("IntoInsumosScreen/{idControlInsumos}",
        arguments = listOf(
            navArgument("idControlInsumos"){type = NavType.LongType}
        )){

        backStackEntry ->
        val idControlInsumos = backStackEntry.arguments?.getLong("idControlInsumos")?: 0
        IntoInsumosScreen(navController, idControlInsumos)

    }
}
}