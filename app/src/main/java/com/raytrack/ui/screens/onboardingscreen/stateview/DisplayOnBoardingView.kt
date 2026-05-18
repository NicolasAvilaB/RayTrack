package com.raytrack.ui.screens.onboardingscreen.stateview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DisplayOnBoardingView(
    navToHome: () -> Unit,
    saveOnBoarding: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                saveOnBoarding.invoke()
                navToHome.invoke()
            }
        ) {
            Text("Entendido!")
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
