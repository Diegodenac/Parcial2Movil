package com.example.myapplication.feature.dollar.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DollarScreen(viewModelDollar: DollarViewModel = koinViewModel()) {
    val state = viewModelDollar.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val stateValue = state.value) {
            is DollarViewModel.DollarUIState.Error -> Text(stateValue.message)
            DollarViewModel.DollarUIState.Loading -> CircularProgressIndicator()
            is DollarViewModel.DollarUIState.Success -> {
                val list = stateValue.dataList

                // Encabezado
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TableHeader("Oficial")
                    TableHeader("Paralelo")
                    TableHeader("USDT")
                    TableHeader("USDC")
                    TableHeader("Fecha")
                }
                Divider()

                // Tabla con datos
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(list) { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TableCell(item.dollarOfficial)
                            TableCell(item.dollarParallel)
                            TableCell(item.dollarUsdt)
                            TableCell(item.dollarUsdc)
                            TableCell(
                                SimpleDateFormat("dd/MM HH:mm", Locale.getDefault())
                                    .format(Date(item.timestamp))
                            )
                        }
                        Divider()
                    }
                }
            }
        }
    }
}

@Composable
fun TableHeader(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun TableCell(text: String?) {
    Text(
        text = text ?: "--",
    )
}
