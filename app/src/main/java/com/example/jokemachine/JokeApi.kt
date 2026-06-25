package com.example.jokemachine

import retrofit2.http.GET

interface JokeApi {
    @GET("random_joke")
    suspend fun getRandomJoke(): Joke
}
