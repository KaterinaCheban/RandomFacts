package com.example.randomfacts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomfacts.model.Fact
import com.example.randomfacts.repository.FactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactViewModel @Inject constructor(
    private val repository: FactRepository
) : ViewModel() {

    private val _fact = MutableStateFlow<Fact?>(null)
    val fact: StateFlow<Fact?> = _fact

    fun loadFact() {
        viewModelScope.launch {
            _fact.value = repository.getRandomFact()
        }
    }
}
