package com.example.km_test.ui.screens.screen1

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.km_test.domain.cekPalindromeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstScreenViewModel @Inject constructor(
    private val cekPalindrome: cekPalindromeUseCase,
): ViewModel() {

    private val _name = MutableLiveData<String>().apply { value = "" }
    val name: LiveData<String> = _name

    fun setName(input: String) {
        _name.value = input
        Log.d("FirstScreenViewModel", "Name set to: $input")
    }

    private val _hasilCek = MutableLiveData<Boolean>()
    val hasilPalindrome: LiveData<Boolean> = _hasilCek

    fun cekPalindrome(input: String) {
        val hasil = cekPalindrome.execute(input)
        _hasilCek.value = hasil
    }

}