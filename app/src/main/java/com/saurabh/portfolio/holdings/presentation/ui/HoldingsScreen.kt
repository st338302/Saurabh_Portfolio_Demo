package com.saurabh.portfolio.holdings.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.saurabh.portfolio.holdings.presentation.viewmodel.HoldingsViewModel

@Composable
fun HoldingsScreen(
    viewModel: HoldingsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    var summaryExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            state.isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                Text(
                    text = "Error: ${state.error}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    HoldingsList(
                        holdings = state.holdings
                    )

                    Spacer(Modifier.height(12.dp))
                }

                state.summary?.let {
                    SummaryCard(
                        summary = it,
                        expanded = summaryExpanded,
                        onExpandToggle = { summaryExpanded = !summaryExpanded },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                    )
                }

                Spacer(Modifier.height(8.dp))

            }
        }
    }
}
