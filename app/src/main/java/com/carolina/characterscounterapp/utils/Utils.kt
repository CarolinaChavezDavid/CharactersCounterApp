package com.carolina.characterscounterapp.utils


fun getCounterList(map: Map<String, Int>): List<String> {
    val words = mutableListOf<String>()
    map.forEach { (text, amount) -> words.add("${text} - ${amount}")  }
    return words
}