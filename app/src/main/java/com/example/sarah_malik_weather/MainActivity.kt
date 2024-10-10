package com.example.sarah_malik_weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sarah_malik_weather.ui.theme.Sarah_Malik_weatherTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge layout rendering
        enableEdgeToEdge()
        // Set the UI content to the Celsius to Fahrenheit converter UI
        setContent {
            Sarah_Malik_weatherTheme {
                // Scaffold provides basic layout structure with padding
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CelsiusToFahrenheitConverter()
                }
            }
        }
    }
}

@Composable
fun CelsiusToFahrenheitConverter() {
    // Celsius temperature state initialized to 0°C
    var celsius by remember { mutableStateOf(0f) }
    // Fahrenheit temperature state initialized to 32°F
    var fahrenheit by remember { mutableStateOf(32f) }

    // Function to convert Celsius to Fahrenheit
    fun celsiusToFahrenheit(celsius: Float): Float {
        // Formula for Celsius to Fahrenheit conversion
        return celsius * 9 / 5 + 32
    }

    // Function to convert Fahrenheit to Celsius
    fun fahrenheitToCelsius(fahrenheit: Float): Float {
        // Formula for Fahrenheit to Celsius conversion
        return (fahrenheit - 32) * 5 / 9
    }

    // Handle Celsius Slider changes
    fun onCelsiusChange(newCelsius: Float) {
        // Update Celsius value
        celsius = newCelsius
        // Update the corresponding Fahrenheit value based on the new Celsius
        fahrenheit = celsiusToFahrenheit(newCelsius)
    }

    // Handle Fahrenheit Slider changes, ensuring it doesn't go below 32°F
    fun onFahrenheitChange(newFahrenheit: Float) {
        // Update Fahrenheit value, ensuring it doesn't drop below 32°F
        fahrenheit = if (newFahrenheit < 32f) 32f else newFahrenheit
        // Update the corresponding Celsius value based on the new Fahrenheit
        celsius = fahrenheitToCelsius(fahrenheit)
    }

    // Message based on the Celsius temperature
    val message = if (celsius <= 20) "I wish it were warmer." else "I wish it were colder."

    // Column layout to vertically arrange the sliders and text
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Padding around the content
        horizontalAlignment = Alignment.CenterHorizontally, // Center the content horizontally
        verticalArrangement = Arrangement.Center // Center the content vertically
    ) {
        // Celsius Slider with a text label
        Text(text = "Celsius: ${celsius.toInt()}°C") // Display Celsius value
        Slider(
            value = celsius, // Slider value for Celsius
            onValueChange = { onCelsiusChange(it) }, // Update Celsius when slider changes
            valueRange = 0f..100f, // Slider range from 0°C to 100°C
            modifier = Modifier.padding(vertical = 16.dp) // Padding around the slider
        )

        // Fahrenheit Slider with a text label
        Text(text = "Fahrenheit: ${fahrenheit.toInt()}°F") // Display Fahrenheit value
        Slider(
            value = fahrenheit, // Slider value for Fahrenheit
            onValueChange = { onFahrenheitChange(it) }, // Update Fahrenheit when slider changes
            valueRange = 32f..212f, // Slider range from 32°F to 212°F
            modifier = Modifier.padding(vertical = 16.dp) // Padding around the slider
        )

        // Display the interesting message based on the Celsius temperature
        Text(text = message, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun CelsiusToFahrenheitConverterPreview() {
    // Preview of the Celsius to Fahrenheit converter
    Sarah_Malik_weatherTheme {
        CelsiusToFahrenheitConverter()
    }
}
