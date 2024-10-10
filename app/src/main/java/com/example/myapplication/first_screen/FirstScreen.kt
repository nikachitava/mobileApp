package com.example.myapplication.first_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.myapplication.R
import androidx.compose.ui.Modifier

@Composable
fun FirstScreen() {

    Box(modifier = Modifier.fillMaxSize()) {
        Button(onClick = {  }) {
            Text(text = stringResource(id = R.string.open_second_screen))
        }
    }
}