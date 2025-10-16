package com.saurabh.portfolio.holdings.presentation.ui

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saurabh.portfolio.R.string
import com.saurabh.portfolio.core.common.formatMoney
import com.saurabh.portfolio.holdings.domain.model.Holding

@Composable
fun HoldingsList(
    holdings: List<Holding>
) {
    LazyColumn {
        items(holdings, key = { it.symbol }) { holding ->
            HoldingRow(
                holding = holding
            )
            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HoldingRow(
    holding: Holding
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = holding.symbol, style = MaterialTheme.typography.titleMedium
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(id = string.net_qty_prefix),
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                        color = Color.Gray
                    )
                    Text(
                        text = holding.quantity.toString(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(id = string.ltp_prefix),
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                        color = Color.Gray
                    )
                    Text(holding.ltp.formatMoney(),
                        style = MaterialTheme.typography.bodyMedium)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(id = string.pnl_prefix),
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                        color = Color.Gray
                    )
                    Text(
                        holding.getPnL().formatMoney(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (holding.getPnL() < 0) Color.Red else Color(0xFF2E7D32)
                    )
                }

            }
        }
    }
}
