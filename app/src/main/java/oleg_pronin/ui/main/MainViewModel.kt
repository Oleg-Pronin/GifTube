package oleg_pronin.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import oleg_pronin.AppState
import oleg_pronin.data.net.GifRepoImpl
import oleg_pronin.domain.entity.Gif
import oleg_pronin.domain.repository.GifRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel(), MainContract.ViewModel {
    override val gifLiveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
    override var position: Int = 0

    private val gifRepo: GifRepo by lazy { GifRepoImpl() }
    private var cache: MutableMap<Int, Gif> = mutableMapOf()

    override fun onNextGif() {
        try {
            gifLiveData.postValue(AppState.Loading)

            if (cache[position + 1] == null) {
                gifRepo.getRandomGif(object : Callback<Gif> {
                    override fun onResponse(call: Call<Gif>, response: Response<Gif>) {
                        val serverResponse: Gif? = response.body()

                        gifLiveData.postValue(
                            if (response.isSuccessful && serverResponse != null) {
                                cache[++position] = serverResponse

                                AppState.Success(serverResponse)
                            } else {
                                AppState.Error(Throwable("Error"))
                            }
                        )
                    }

                    override fun onFailure(call: Call<Gif>, t: Throwable) {
                        gifLiveData.postValue(AppState.Error(t))
                    }
                })
            } else {
                gifLiveData.postValue(AppState.Success(cache[++position]))
            }
        } catch (error: Throwable) {
            gifLiveData.postValue(AppState.Error(error))
        }
    }

    override fun onGifPrevious() {
        cache[position - 1]?.let {
            gifLiveData.postValue(AppState.Success(it))
            --position
        }
    }
}