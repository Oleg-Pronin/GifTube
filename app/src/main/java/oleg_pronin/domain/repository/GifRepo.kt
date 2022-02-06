package oleg_pronin.domain.repository

import oleg_pronin.domain.entity.Gif

interface GifRepo {
    fun getRandomGif(callback: retrofit2.Callback<Gif>)
}