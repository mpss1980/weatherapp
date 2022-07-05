package br.com.coupledev.weatherapp.domain.repositories

import br.com.coupledev.weatherapp.domain.util.Resource
import br.com.coupledev.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}