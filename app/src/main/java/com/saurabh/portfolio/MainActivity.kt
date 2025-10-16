package com.saurabh.portfolio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.saurabh.portfolio.holdings.presentation.ui.HoldingsScreen
import com.saurabh.portfolio.ui.theme.HoldingsDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HoldingsDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    HoldingsScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HoldingsPreview() {
    HoldingsDemoTheme {
        HoldingsScreen()
    }
}