package com.zero.graphql.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.zero.graphql.CountriesViewModel
import com.zero.graphql.domain.DetailedCountry
import com.zero.graphql.domain.SimpleCountry

@Composable
fun MainScreen(
    state: CountriesViewModel.CountriesState,
    onSelectCountry: (code: String) -> Unit,
    onDismissCountryDialog: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.countries) {
                    CountryItem(
                        it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelectCountry(it.code)
                            }
                    )
                }
            }
            state.selectedCountry?.let {
                CountryDialog(
                    country = it,
                    onDismiss = onDismissCountryDialog,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun CountryDialog(
    country: DetailedCountry,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val languages = remember(country.languages) {
        country.languages.joinToString()
    }
    Dialog(onDismissRequest = onDismiss) {
        Column(modifier = modifier) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = country.emoji, fontSize = 30.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = country.name, fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = country.capital, fontSize = 24.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = country.continent, fontSize = 24.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = languages, fontSize = 24.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = country.currency, fontSize = 24.sp)
        }
    }
}

@Composable
fun CountryItem(
    country: SimpleCountry,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = country.emoji,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = country.name,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = country.capital)
        }
    }
}
