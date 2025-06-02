package com.nixoncortes1005.apphuevos2

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.nixoncortes1005.apphuevos2.Balance.Presentations.viewModels.BalanceViewModel
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Components.CardGrandeActivity
import com.nixoncortes1005.apphuevos2.di.Navigation.ConfigNavController
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Screens.HomePage
import com.nixoncortes1005.apphuevos2.Pedidos.Presentations.Screens.PedidosScreen
import com.nixoncortes1005.apphuevos2.ui.theme.AppHuevos2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI()
        enableEdgeToEdge()
        setContent {

            AppHuevos2Theme {
                val viewModel: BalanceViewModel = hiltViewModel()
                LaunchedEffect(Unit) {
                    viewModel.getBalanceByMes()
                }
                val navController = rememberNavController()
                ConfigNavController(navController = navController)
                }
                }
            }

    fun hideSystemUI() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                )
    }

}
