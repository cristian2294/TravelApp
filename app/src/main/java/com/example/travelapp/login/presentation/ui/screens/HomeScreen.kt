package com.example.travelapp.login.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewGreeting() {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Sign Out")
        }
    }
}
