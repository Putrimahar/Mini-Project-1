package com.putrimaharani0087.miniproject1

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.putrimaharani0087.miniproject1.navigation.SetupNavGraph
import com.putrimaharani0087.miniproject1.ui.theme.MiniProject1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniProject1Theme {
                val navController = rememberNavController()
                SetupNavGraph(navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {
        innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            ScreenContent(navController)
        }
    }
}

@Composable
fun ScreenContent(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.app_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        Opsi(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Opsi(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Pilih Rumus") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(0.7f),
                trailingIcon = {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Dropdown Icon"
                    )
                }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Kecepatan") },
                    onClick = {
                        selectedOption = "Kecepatan"
                        expanded = false
                        navController.navigate("kecepatan") {
                            popUpTo("main") {
                                inclusive = false
                            }
                        }
                    }
                )
                DropdownMenuItem(
                    text = { Text("Jarak") },
                    onClick = {
                        selectedOption = "Jarak"
                        expanded = false
                        navController.navigate("Jarak") {
                            popUpTo("main") {
                                inclusive = false
                            }
                        }
                    }
                )
                DropdownMenuItem(
                    text = { Text("Waktu") },
                    onClick = {
                        selectedOption = "Waktu"
                        expanded = false
                        navController.navigate("Waktu") {
                            popUpTo("main") {
                                inclusive = false
                            }
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenKecepatan(navController: NavController) {
    var jarak by remember { mutableStateOf("") }
    var jarakError by remember { mutableStateOf(false) }

    var waktu by remember { mutableStateOf("") }
    var waktuError by remember { mutableStateOf(false) }

    var hasil by remember { mutableFloatStateOf(0f) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = stringResource(id = R.string.app_intro),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
            Opsi(navController)
            Text(
                text = stringResource(id = R.string.menghitung_kecepatan),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            OutlinedTextField(
                value = jarak,
                onValueChange = { jarak = it },
                label = { Text(text = stringResource(R.string.jarak)) },
                trailingIcon = { IconPicker(jarakError, "km") },
                supportingText = { ErrorHint(jarakError) },
                isError = jarakError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = waktu,
                onValueChange = { waktu = it },
                label = { Text(text = stringResource(R.string.waktu)) },
                trailingIcon = { IconPicker(waktuError, "jam") },
                supportingText = { ErrorHint(waktuError) },
                isError = waktuError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    jarakError = (jarak == "" || jarak == "0")
                    waktuError = (waktu == "" || waktu == "0")
                    if (jarakError || waktuError) return@Button

                    hasil = hitungKecepatan(jarak.toFloat(), waktu.toFloat())
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.hitung))
            }
            if (hasil != 0f) {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp
                )
                Text(
                    text = stringResource(R.string.hasilKecepatan_x, hasil),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenJarak(navController: NavController) {
    var kecepatan by remember { mutableStateOf("") }
    var kecepatanError by remember { mutableStateOf(false) }

    var waktu by remember { mutableStateOf("") }
    var waktuError by remember { mutableStateOf(false) }

    var hasil by remember { mutableFloatStateOf(0f) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = stringResource(id = R.string.app_intro),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
            Opsi(navController)
            Text(
                text = stringResource(id = R.string.menghitung_jarak),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            OutlinedTextField(
                value = kecepatan,
                onValueChange = { kecepatan = it },
                label = { Text(text = stringResource(R.string.kecepatan)) },
                trailingIcon = { IconPicker(kecepatanError, "km/jam") },
                supportingText = { ErrorHint(kecepatanError) },
                isError = kecepatanError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = waktu,
                onValueChange = { waktu = it },
                label = { Text(text = stringResource(R.string.waktu)) },
                trailingIcon = { IconPicker(waktuError, "jam") },
                supportingText = { ErrorHint(waktuError) },
                isError = waktuError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    kecepatanError = (kecepatan == "" || kecepatan == "0")
                    waktuError = (waktu == "" || waktu == "0")
                    if (kecepatanError || waktuError) return@Button

                    hasil = hitungJarak(kecepatan.toFloat(), waktu.toFloat())
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.hitung))
            }
            if (hasil != 0f) {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp
                )
                Text(
                    text = stringResource(R.string.hasilJarak_x, hasil),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenWaktu(navController: NavController) {
    var jarak by remember { mutableStateOf("") }
    var jarakError by remember { mutableStateOf(false) }

    var kecepatan by remember { mutableStateOf("") }
    var kecepatanError by remember { mutableStateOf(false) }

    var hasil by remember { mutableFloatStateOf(0f) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = stringResource(id = R.string.app_intro),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
            Opsi(navController)
            Text(
                text = stringResource(id = R.string.menghitung_waktu),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            OutlinedTextField(
                value = jarak,
                onValueChange = { jarak = it },
                label = { Text(text = stringResource(R.string.jarak)) },
                trailingIcon = { IconPicker(jarakError, "km") },
                supportingText = { ErrorHint(jarakError) },
                isError = jarakError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = kecepatan,
                onValueChange = { kecepatan = it },
                label = { Text(text = stringResource(R.string.kecepatan)) },
                trailingIcon = { IconPicker(kecepatanError, "km/jam") },
                supportingText = { ErrorHint(kecepatanError) },
                isError = kecepatanError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    jarakError = (jarak == "" || jarak == "0")
                    kecepatanError = (kecepatan == "" || kecepatan == "0")
                    if (jarakError || kecepatanError) return@Button

                    hasil = hitungWaktu(jarak.toFloat(), kecepatan.toFloat())
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.hitung))
            }
            if (hasil != 0f) {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp
                )
                Text(
                    text = stringResource(R.string.hasilWaktu_x, hasil),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.input_invalid))
    }
}

private fun hitungKecepatan(jarak: Float, waktu: Float): Float {
    return jarak / waktu
}

private fun hitungJarak(kecepatan: Float, waktu: Float): Float {
    return kecepatan * waktu
}

private fun hitungWaktu(jarak: Float, kecepatan: Float): Float {
    return jarak / kecepatan
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    MiniProject1Theme {
        val navController = rememberNavController()
        MainScreen(navController)
    }
}