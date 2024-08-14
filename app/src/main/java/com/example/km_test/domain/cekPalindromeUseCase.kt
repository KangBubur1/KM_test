package com.example.km_test.domain

import javax.inject.Inject

class cekPalindromeUseCase @Inject constructor() {
    fun execute(input: String): Boolean {
        val reversed = input.reversed()
        return input.equals(reversed, ignoreCase = true)
    }
}