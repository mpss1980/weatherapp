package br.com.coupledev.weatherapp.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import br.com.coupledev.weatherapp.data.mappers.toWeatherInfo
import br.com.coupledev.weatherapp.data.remote.WeatherApi
import br.com.coupledev.weatherapp.domain.repositories.WeatherRepository
import br.com.coupledev.weatherapp.domain.util.Resource
import br.com.coupledev.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            val response = api.getWeatherData(lat = lat, long = long)
            Resource.Success(
                data = response.toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}