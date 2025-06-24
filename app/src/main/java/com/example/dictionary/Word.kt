package com.example.dictionary

import java.io.Serializable

data class Word(var id: Int, var english: String, var turkish: String) : Serializable {
}