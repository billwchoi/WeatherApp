package com.plcoding.weatherapp.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.weatherapp.presentation.ui.theme.DarkBlue
import com.plcoding.weatherapp.presentation.ui.theme.DeepBlue
import com.plcoding.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            println("1----------onCreate call weather data ")
            println("1----------MainActivity viewModel.loadWeatherInfo() : $viewModel")
            viewModel.loadWeatherInfo()
        }
        // 로케이션 정보 가져오기
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ))
        // 화면 출력
        setContent {
            WeatherAppTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) { //Column 아래로
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(DarkBlue)
                    ) {
                        println("----------viewModel.state WeatherState :${viewModel.state}")
                        // 없어도 프로그램 수행이 지장 없음 말 그래도 카드 형태
                        WeatherCard(
                            state = viewModel.state,
                            backgroundColor = DeepBlue
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Greeting("Bill")
                        Spacer(modifier = Modifier.height(16.dp))
                        // 시간대별 기온
                        WeatherForecast(state = viewModel.state)
                    }
                    // 데이타 가져올 때 보이는 동글동글 도는 프로그레스 바
                    if(viewModel.state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center) // 위아래중 가운데
//                            modifier = Modifier.align(Alignment.TopCenter) // 위에서 센터 정렬
//                            modifier = Modifier.align(Alignment.BottomCenter) // 아래서 센터 정렬
                        )
                    }
                    // 에러시 보이는 화면
                    viewModel.state.error?.let { error ->
                        Text(
                            text = error,
                            color = Color.Red,
                            textAlign = TextAlign.Center, // 글자를 가운데로 모이게 함
//                            textAlign = TextAlign.Right, // 글자를 오른 쪽에 붙임
//                            textAlign = TextAlign.Justify, // 글자를 양옆에 붙임
//                            textAlign = TextAlign.End, // Right
                            modifier = Modifier.align(Alignment.Center) // 위아래 가운데 좌우 가운데
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(
        name: String,
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Hello $name!",
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }

}