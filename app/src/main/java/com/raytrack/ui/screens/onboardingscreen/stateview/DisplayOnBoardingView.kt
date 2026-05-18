package com.raytrack.ui.screens.onboardingscreen.stateview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DisplayOnBoardingView(
    navToHome: () -> Unit,
    saveOnBoarding: () -> Unit,
) {
    val pages = listOf(
        "Bienvenido a la app 🚀",
        "Controla todo fácilmente 📊",
        "Listo para comenzar 🎉"
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val isLastPage = pagerState.currentPage == pages.lastIndex

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = pages[page])
            }
        }

        AnimatedVisibility(
            visible = isLastPage,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                    saveOnBoarding()
                    navToHome()
                }
            ) {
                Text("Entendido!")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayOnBoardingPreview(){
    DisplayOnBoardingView(
        navToHome = { },
        saveOnBoarding = { },
    )
}
