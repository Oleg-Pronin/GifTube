package oleg_pronin.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.HapticFeedbackConstants
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import oleg_pronin.AppState
import oleg_pronin.databinding.ActivityMainBinding
import oleg_pronin.domain.entity.Gif

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainContract.ViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUIEvent()
        initViewModel()

        viewModel.onNextGif()
    }

    private fun initUIEvent() {
        binding.buttonNext.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            viewModel.onNextGif()
        }

        binding.buttonBefore.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            viewModel.onGifPrevious()
        }
    }

    private fun initViewModel() {
        viewModel.gifLiveData?.observe(this) { renderData(it) }
    }

    private fun renderData(appState: AppState) = when (appState) {
        is AppState.Success -> {
            val gif = appState.data as Gif

            gif.description?.let{
                binding.description.text = it
            }

            gif.gifURL?.let {
                Glide
                    .with(this)
                    .load(it)
                    .into(binding.image)
            }

            showOrHide(false)
        }
        is AppState.Loading -> {
            showOrHide(true)
        }
        is AppState.Error -> {
            binding.messageError.text = appState.error.message
            showOrHide(false)
        }
    }

    private fun showOrHide(show: Boolean) {
        binding.progressBar.isVisible = show
        binding.image.isVisible = !show && binding.messageError.text == ""
        binding.description.isVisible = !show && binding.messageError.text == ""

        binding.buttonBefore.isVisible = viewModel.position > 1

        if (show) {
            binding.messageError.text = ""
            binding.messageError.isVisible = false
        } else {
            binding.messageError.isVisible = true
        }
    }
}
