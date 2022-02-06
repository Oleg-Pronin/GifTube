package oleg_pronin.data.net

import oleg_pronin.data.net.api.DevelopersLifeApi
import oleg_pronin.data.net.retrofit.RetrofitClient
import oleg_pronin.domain.entity.Gif
import oleg_pronin.domain.repository.GifRepo
import retrofit2.Callback

private const val BASE_URL = "https://developerslife.ru/"

class GifRepoImpl : GifRepo {
    private val retrofit = RetrofitClient.getClient(BASE_URL)
    private var api: DevelopersLifeApi = retrofit.create(DevelopersLifeApi::class.java)

    override fun getRandomGif(callback: Callback<Gif>) {
        api.getRandom().enqueue(callback)
    }
}