package com.example.jokemachine

import junit.framework.TestCase.assertTrue
import org.junit.Test

class UiStateTest {

    @Test
    fun `ui state loading should be of type loading`() {
        val state = UiState.Loading
        assertTrue(state is UiState.Loading)
    }

    @Test
    fun `ui state success should contain a joke`() {
        val joke = Joke(1, "test", "Setup", "Punchline")
        val state = UiState.Success(joke)
        assertTrue(state is UiState.Success)
        assertTrue(state.joke == joke)
    }

    @Test
    fun `ui state error should contain error message`() {
        val state = UiState.Error("Something went wrong")
        assertTrue(state is UiState.Error)
        assertTrue(state.message == "Something went wrong")
    }
}