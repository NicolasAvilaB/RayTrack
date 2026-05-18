package com.raytrack.ui.navigation.extensions

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

fun NavBackStack<NavKey>.navigateTo(screens: NavKey) {
    add(screens)
}

fun NavBackStack<NavKey>.navigateAfterOnBoarding(home: NavKey) {
    clear()
    add(home)
}

fun NavBackStack<NavKey>.back() {
    if (size > 1) {
        removeLastOrNull()
    }
}

fun NavBackStack<NavKey>.backTo(targetScreens: NavKey) {
    if (isEmpty()) return
    if (targetScreens !in this) return

    while (isNotEmpty() && last() != targetScreens)
        removeLastOrNull()
}
