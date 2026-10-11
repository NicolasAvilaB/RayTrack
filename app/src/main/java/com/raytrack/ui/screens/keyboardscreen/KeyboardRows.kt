package com.raytrack.ui.screens.keyboardscreen

internal fun getKeyboardRows(language: String): List<List<String>> {
    return when (language) {
        "es" -> listOf(
            listOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
            listOf("A", "S", "D", "F", "G", "H", "J", "K", "L", "Ñ"),
            listOf("Z", "X", "C", "V", "B", "N", "M")
        )

        "en" -> listOf(
            listOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
            listOf("A", "S", "D", "F", "G", "H", "J", "K", "L"),
            listOf("Z", "X", "C", "V", "B", "N", "M")
        )

        "ru" -> listOf(
            listOf("Й", "Ц", "У", "К", "Е", "Н", "Г", "Ш", "Щ", "З", "Х"),
            listOf("Ф", "Ы", "В", "А", "П", "Р", "О", "Л", "Д", "Ж", "Э"),
            listOf("Я", "Ч", "С", "М", "И", "Т", "Ь", "Б", "Ю")
        )

        else -> listOf(
            listOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
            listOf("A", "S", "D", "F", "G", "H", "J", "K", "L"),
            listOf("Z", "X", "C", "V", "B", "N", "M")
        )
    }
}
