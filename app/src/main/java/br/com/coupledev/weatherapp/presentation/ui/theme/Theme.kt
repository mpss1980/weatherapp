package br.com.coupledev.weatherapp.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import br.com.coupledev.weatherapp.presentaion.theme.Shapes
import br.com.coupledev.weatherapp.presentaion.theme.Typography

@Composable
fun WeatherAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}