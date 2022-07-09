package br.com.coupledev.weatherapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.coupledev.weatherapp.domain.location.LocationTracker
import br.com.coupledev.weatherapp.domain.repositories.WeatherRepository
import br.com.coupledev.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )

            locationTracker.getCurrentLocation()?.let { location ->
                state = when (val result = repository.getWeatherData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        state.copy(
                            weatherInfo = result.data,
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }

            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
            }
        }
    }
}