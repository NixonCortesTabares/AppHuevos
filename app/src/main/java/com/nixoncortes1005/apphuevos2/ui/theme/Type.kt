package com.nixoncortes1005.apphuevos2.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nixoncortes1005.apphuevos2.R

val interFamily = FontFamily(
    Font(R.font.interregular, FontWeight.Normal),
    Font(R.font.bold, FontWeight.Bold),
    Font(R.font.semibold, FontWeight.SemiBold),
)
// Set of Material typography styles to start with


@Composable
fun fuenteAdaptable(): Typography{
    val anchoPantalla = LocalConfiguration.current.screenWidthDp
    val fuenteBody:TextUnit
    val fuenteTitulos: TextUnit
    val fuenteSubtitulos: TextUnit
    val fuenteBotones: TextUnit
    val fuentes = when{

        anchoPantalla < 360 -> {
            fuenteBody = 12.sp
            fuenteTitulos = 25.sp
            fuenteSubtitulos = 22.sp
            fuenteBotones = 10.sp
        }
        anchoPantalla < 400 ->  {
            fuenteBody = 16.sp
            fuenteTitulos = 35.sp
            fuenteSubtitulos = 28.sp
            fuenteBotones = 12.sp
        }
        else ->  {
            fuenteBody = 18.sp
            fuenteTitulos = 40.sp
            fuenteSubtitulos = 30.sp
            fuenteBotones = 16.sp
        }

    }

    return  Typography(
        bodyLarge = TextStyle(
            fontFamily = interFamily,
            fontWeight = FontWeight.Normal,
            fontSize = fuenteBody,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        titleLarge = TextStyle(
            fontFamily = interFamily,
            fontWeight = FontWeight.Bold,
            fontSize = fuenteTitulos,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
            fontFamily = interFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = fuenteSubtitulos,
            lineHeight = 16.sp,
            letterSpacing = 0.3.sp
        ),
        labelLarge = TextStyle(
            fontFamily = interFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = fuenteBotones,
            lineHeight = 20.sp
        )

    )

}
