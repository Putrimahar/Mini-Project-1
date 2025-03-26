package com.putrimaharani0087.miniproject1

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
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
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.putrimaharani0087.miniproject1.navigation.Screen
import com.putrimaharani0087.miniproject1.ui.theme.MiniProject1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Rumus.route)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_rumus),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
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
    val defaultOption = stringResource(id = R.string.pilih_rumus)
    var selectedOption by rememberSaveable { mutableStateOf(defaultOption) }
    val kecepatanText = stringResource(id = R.string.kecepatan)
    val jarakText = stringResource(id = R.string.jarak)
    val waktuText = stringResource(id = R.string.waktu)

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
                    text = {Text(text = kecepatanText)},
                    onClick = {
                        selectedOption = kecepatanText
                        expanded = false
                        navController.navigate("Kecepatan") {
                            popUpTo("main") {
                                inclusive = false
                            }
                        }
                    }
                )
                DropdownMenuItem(
                    text = {Text(text = jarakText)},
                    onClick = {
                        selectedOption = jarakText
                        expanded = false
                        navController.navigate("Jarak") {
                            popUpTo("main") {
                                inclusive = false
                            }
                        }
                    }
                )
                DropdownMenuItem(
                    text = {Text(text = waktuText)},
                    onClick = {
                        selectedOption = waktuText
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
    var jarak by rememberSaveable { mutableStateOf("") }
    var jarakError by rememberSaveable { mutableStateOf(false) }

    var waktu by rememberSaveable { mutableStateOf("") }
    var waktuError by rememberSaveable { mutableStateOf(false) }

    var hasil by rememberSaveable { mutableFloatStateOf(0f) }

    val context = LocalContext.current

    val kmText = stringResource(id = R.string.km)
    val jamText = stringResource(id = R.string.jam)


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,

                    ),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Rumus.route)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_rumus),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
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
                trailingIcon = { IconPicker(jarakError, kmText) },
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
                trailingIcon = { IconPicker(waktuError, jamText) },
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
                Button(
                    onClick = {
                        shareData(
                            context = context,
                            message = context.getString(R.string.bagikan_hasilKecepatan,
                                jarak, waktu, hasil
                            )
                        )
                    },
                    modifier = Modifier.padding(top = 8.dp),
                    contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
                ) {
                    Text(text = stringResource(R.string.bagikan))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenJarak(navController: NavController) {
    var kecepatan by rememberSaveable { mutableStateOf("") }
    var kecepatanError by rememberSaveable { mutableStateOf(false) }

    var waktu by rememberSaveable { mutableStateOf("") }
    var waktuError by rememberSaveable { mutableStateOf(false) }

    var hasil by rememberSaveable { mutableFloatStateOf(0f) }

    val context = LocalContext.current

    val kmjamText = stringResource(id = R.string.km_jam)
    val jamText = stringResource(id = R.string.jam)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Rumus.route)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_rumus),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
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
                trailingIcon = { IconPicker(kecepatanError, kmjamText) },
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
                trailingIcon = { IconPicker(waktuError, jamText) },
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
                Button(
                    onClick = {
                        shareData(
                            context = context,
                            message = context.getString(R.string.bagikan_hasiljarak,
                                kecepatan, waktu, hasil
                            )
                        )
                    },
                    modifier = Modifier.padding(top = 8.dp),
                    contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
                ) {
                    Text(text = stringResource(R.string.bagikan))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenWaktu(navController: NavController) {
    var jarak by rememberSaveable { mutableStateOf("") }
    var jarakError by rememberSaveable { mutableStateOf(false) }

    var kecepatan by rememberSaveable { mutableStateOf("") }
    var kecepatanError by rememberSaveable { mutableStateOf(false) }

    var hasil by rememberSaveable { mutableFloatStateOf(0f) }

    val context = LocalContext.current

    val kmText = stringResource(id = R.string.km)
    val kmjamText = stringResource(id = R.string.km_jam)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Rumus.route)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_rumus),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
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
                trailingIcon = { IconPicker(jarakError, kmText) },
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
                trailingIcon = { IconPicker(kecepatanError, kmjamText) },
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
                Button(
                    onClick = {
                        shareData(
                            context = context,
                            message = context.getString(R.string.bagikan_hasilWaktu,
                                jarak, kecepatan, hasil
                            )
                        )
                    },
                    modifier = Modifier.padding(top = 8.dp),
                    contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
                ) {
                    Text(text = stringResource(R.string.bagikan))
                }
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

private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
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
        MainScreen(rememberNavController())
    }
}