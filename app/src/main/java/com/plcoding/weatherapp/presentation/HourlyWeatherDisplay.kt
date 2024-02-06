package com.plcoding.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.plcoding.weatherapp.domain.weather.WeatherData
import java.time.format.DateTimeFormatter

@Composable
fun HourlyWeatherDisplay(
    weatherData: WeatherData,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White
) {
    println("----------HourlyWeatherDisplay weatherData:$weatherData")
    println("----------HourlyWeatherDisplay weatherData.weatherType.iconRes:${weatherData.weatherType.iconRes}")
    println("----------HourlyWeatherDisplay weatherData.temperatureCelsius:${weatherData.temperatureCelsius}")

    // 리멤버 기억하기
    val formattedTime = remember(weatherData) {
        weatherData.time.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )
    }
    // 아래로
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally, // 수평 정렬 = 가로 중앙 정렬
        verticalArrangement = Arrangement.SpaceBetween // 수직 정렬 = 가운데 간격 두기
    ) {
        Text(
            text = formattedTime,
            color = Color.LightGray
        )
        Image(
            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = null,
            modifier = Modifier.width(40.dp)
        )
        Text(
            text = "${weatherData.temperatureCelsius}°C",
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}