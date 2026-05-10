package com.raytrack.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun OnBoardingScreen(
    navToHome: (String) -> Unit
) {
    Column {
        LazyColumn {
            items(40) {
                Box(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .height(60.dp)
                        .fillMaxWidth()
                        .clickable {
                            navToHome.invoke(it.toString())
                        },
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Text(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Hola ${it}"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview(){
    OnBoardingScreen(
        navToHome = { }
    )
}
