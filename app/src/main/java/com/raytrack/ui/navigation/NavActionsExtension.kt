package com.raytrack.ui.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

fun NavBackStack<NavKey>.navigateTo(screens: NavKey) {
    add(screens)
}

fun NavBackStack<NavKey>.back() {
    if (isEmpty()) return
    removeLastOrNull()
}

fun NavBackStack<NavKey>.backTo(targetScreens: NavKey) {
    if (isEmpty()) return
    if (targetScreens !in this) return

    while (isNotEmpty() && last() != targetScreens)
        removeLastOrNull()
}
