package com.example.jokemachine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val customColorScheme = lightColorScheme(
                primary = Color(0xFF000000),
                onPrimary = Color(0xFFFFFFFF),
                secondary = Color(0xFFE19720),
                onSecondary = Color(0xFF000000),
                background = Color(0xFFFAD61D),
                onBackground = Color(0xFF000000),
                surface = Color(0xFFFFFFFF),
                onSurface = Color(0xFF000000),
                error = Color(0xFFF62D14),
                onError = Color(0xFFFFFFFF)
            )

            MaterialTheme(
                colorScheme = customColorScheme,
                typography = Typography()
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JokeApp()
                }
            }
        }
    }
}

@Composable
fun JokeApp() {
    val viewModel: MainViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    JokeContent(uiState, onLoadJoke = { viewModel.loadRandomJoke() })
}

@Composable
fun JokeContent(uiState: UiState, onLoadJoke: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator(color = Color(0xFF000000))
                Text(
                    text = "Loading joke...",
                    modifier = Modifier.padding(top = 16.dp),
                    color = Color(0xFF000000)
                )
            }
            is UiState.Success -> {
                val joke = uiState.joke
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFFFFF),
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = joke.setup,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = joke.punchline,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = onLoadJoke,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE19720),
                        contentColor = Color(0xFF000000)
                    )
                ) {
                    Text("New Joke")
                }
            }
            is UiState.Error -> {
                Text(
                    text = "Error: ${uiState.message}",
                    color = Color(0xFFF62D14),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onLoadJoke,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE19720),
                        contentColor = Color(0xFF000000)
                    )
                ) {
                    Text("Try Again")
                }
            }
        }
    }
}

// Previews
@Preview(showBackground = true)
@Composable
fun PreviewJokeLoading() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            JokeContent(uiState = UiState.Loading, onLoadJoke = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewJokeSuccess() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            JokeContent(
                uiState = UiState.Success(
                    Joke(
                        id = 1,
                        type = "general",
                        setup = "Why don't scientists trust atoms?",
                        punchline = "Because they make up everything!"
                    )
                ),
                onLoadJoke = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewJokeError() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            JokeContent(
                uiState = UiState.Error("Network error. Please try again."),
                onLoadJoke = {}
            )
        }
    }
}