package com.mk.weathersnap.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mk.weathersnap.domain.model.City
import com.mk.weathersnap.domain.repository.WeatherRepository
import com.mk.weathersnap.util.ErrorType
import com.mk.weathersnap.util.UiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchUiViewModel @Inject constructor(
    private val repo : WeatherRepository
) : ViewModel(){

    private val queryFlow = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val state: StateFlow<UiResult<List<City>>> =
        queryFlow
            .debounce(500) // avoid API spam
            .map { it.trim() }
            .filter { it.length > 2 } // match your UI rule
            .distinctUntilChanged()
            .flatMapLatest { query ->
                repo.getCitySuggestion(query)
            }
            .onStart {
                emit(UiResult.Empty) // initial state
            }
            .catch { e ->
                emit(
                    UiResult.Error(
                        message = e.message ?: "Something went wrong",
                        type = ErrorType.UNKNOWN
                    )
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = UiResult.Empty
            )

    fun onQueryChange(query: String) {
        queryFlow.value = query
    }
}