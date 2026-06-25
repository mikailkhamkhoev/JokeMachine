package com.example.jokemachine

class JokeRepository {
    private val api = RetrofitClient.api

    suspend fun getRandomJoke(): Joke {
        return api.getRandomJoke()
    }
}
