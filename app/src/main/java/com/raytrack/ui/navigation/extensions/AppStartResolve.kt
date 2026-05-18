package com.raytrack.ui.navigation.extensions

import androidx.lifecycle.ViewModel
import com.raytrack.data.remote.onboarding.OnBoardingRepository
import com.raytrack.ui.navigation.NavRoutes
import kotlinx.coroutines.flow.first

internal class AppStartResolve(
    private val repository: OnBoardingRepository
) : ViewModel() {

    suspend fun resolve(): NavRoutes {
        val viewed = repository.isOnBoardingViewed().first()

        return if (viewed) {
            NavRoutes.HomeNav
        } else {
            NavRoutes.OnBoardingNav
        }
    }
}
