package oleg_pronin.ui.main

import androidx.lifecycle.MutableLiveData
import oleg_pronin.AppState

class MainContract {
    interface ViewModel {
        val gifLiveData: MutableLiveData<AppState>?
        var position: Int

        fun onNextGif()
        fun onGifPrevious()
    }
}