package com.example.jokemachine

import junit.framework.TestCase.assertEquals
import org.junit.Test

class JokeTest {

    @Test
    fun `joke data class should have correct fields`() {
        val joke = Joke(
            id = 123,
            type = "general",
            setup = "Why don't scientists trust atoms?",
            punchline = "Because they make up everything!"
        )

        assertEquals(123, joke.id)
        assertEquals("general", joke.type)
        assertEquals("Why don't scientists trust atoms?", joke.setup)
        assertEquals("Because they make up everything!", joke.punchline)
    }
}