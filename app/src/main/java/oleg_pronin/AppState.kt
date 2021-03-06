package oleg_pronin

sealed class AppState {
    data class Success(val data: Any?) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}