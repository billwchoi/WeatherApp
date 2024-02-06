package com.plcoding.weatherapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    println("----------WeatherState state:$state")
    println("----------WeatherState state.weatherInfo?.weatherDataPerDay?.get(0):${state.weatherInfo?.weatherDataPerDay?.get(0)}")
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->
        // 아래로
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            // 첫째 줄
            Text(
                text = "Today",
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            // 둘째
            LazyRow(content = {
                items(data) { weatherData ->
                    println("----------LazyRow weatherData:$weatherData")

                    HourlyWeatherDisplay(
                        weatherData = weatherData,
                        modifier = Modifier
                            .height(100.dp)
                            .padding(horizontal = 16.dp)
                    )
                }
            })
        }
    }
}