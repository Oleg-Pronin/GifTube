package oleg_pronin.data.net.api

import oleg_pronin.domain.entity.Gif
import retrofit2.Call
import retrofit2.http.GET

interface DevelopersLifeApi {
    @GET("random?json=true")
    fun getRandom(): Call<Gif>
}