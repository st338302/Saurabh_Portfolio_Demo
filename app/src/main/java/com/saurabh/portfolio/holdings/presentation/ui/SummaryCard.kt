package com.saurabh.portfolio.holdings.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saurabh.portfolio.core.common.formatMoney
import com.saurabh.portfolio.core.common.formatPercent
import com.saurabh.portfolio.holdings.domain.model.PortfolioSummary

@Composable
fun SummaryCard(
    summary: PortfolioSummary,
    expanded: Boolean,
    onExpandToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .animateContentSize()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {

            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(top = 8.dp)) {
                    SummaryRow(
                        buildAnnotatedString {
                            append("Current Value")
                            withStyle(
                                SpanStyle(
                                    fontSize = 14.sp,
                                    baselineShift = BaselineShift.Superscript,
                                    color = Color.Gray
                                )
                            ) { append("*") }
                        }, summary.currentValue.formatMoney(), valueColor = Color.Gray
                    )
                    SummaryRow(
                        buildAnnotatedString {
                            append("Total Investment")
                            withStyle(
                                SpanStyle(
                                    fontSize = 14.sp,
                                    baselineShift = BaselineShift.Superscript,
                                    color = Color.Gray
                                )
                            ) { append("*") }
                        }, summary.totalInvestment.formatMoney(), valueColor = Color.Gray
                    )
                    SummaryRow(
                        buildAnnotatedString {
                            append("Today's Profit & Loss")
                            withStyle(
                                SpanStyle(
                                    fontSize = 14.sp,
                                    baselineShift = BaselineShift.Superscript,
                                    color = Color.Gray
                                )
                            ) { append("*") }
                        },
                        summary.todaysPnl.formatMoney(),
                        valueColor = if (summary.todaysPnl < 0) Color.Red else Color(0xFF2E7D32)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .clickable { onExpandToggle() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = buildAnnotatedString {
                            append("Profit & Loss")
                            withStyle(
                                SpanStyle(
                                    fontSize = 14.sp,
                                    baselineShift = BaselineShift.Superscript,
                                    color = Color.Gray
                                )
                            ) { append("*") }
                        }.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowDown // pointing down when expanded
                        else Icons.Default.KeyboardArrowUp,
                        contentDescription = if (expanded) "Collapse" else "Expand",
                        tint = Color.Gray,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Text(
                    text = "${summary.totalPnl.formatMoney()} (${
                        summary.getTotalPnLPercent().formatPercent()
                    })",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    color = if (summary.totalPnl < 0) Color.Red else Color(0xFF2E7D32)
                )
            }
        }
    }
}

@Composable
fun SummaryRow(
    label: AnnotatedString, value: String, valueColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            color = valueColor
        )
    }
}
