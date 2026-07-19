package com.raytrack.ui.navigation.extensions

import androidx.lifecycle.ViewModel
import com.raytrack.data.repository.onboarding.OnBoardingUseCase
import com.raytrack.ui.navigation.NavRoutes
import kotlinx.coroutines.flow.first

internal class AppStartResolve(
    private val useCase: OnBoardingUseCase
) : ViewModel() {

    suspend fun resolve(): NavRoutes {
        val viewed = useCase.isOnBoardingViewed().first()

        return if (viewed) {
            NavRoutes.HomeNav
        } else {
            NavRoutes.OnBoardingNav
        }
    }
}
