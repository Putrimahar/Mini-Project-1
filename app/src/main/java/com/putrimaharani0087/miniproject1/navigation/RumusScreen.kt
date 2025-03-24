package com.putrimaharani0087.miniproject1.navigation

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.putrimaharani0087.miniproject1.R
import com.putrimaharani0087.miniproject1.ui.theme.MiniProject1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RumusScreen() {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.tentang_rumus))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {
        innerPadding ->
        Text(
            text = stringResource(R.string.penjelasan),
            modifier = Modifier.padding(innerPadding).padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RumusScreenPreview() {
    MiniProject1Theme {
        RumusScreen()
    }
}