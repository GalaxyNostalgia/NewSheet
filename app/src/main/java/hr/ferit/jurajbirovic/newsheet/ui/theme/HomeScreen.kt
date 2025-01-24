package hr.ferit.jurajbirovic.newsheet.ui

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import hr.ferit.jurajbirovic.newsheet.viewmodel.CharacterViewModel

@Composable
fun HomeScreen(characterViewModel: CharacterViewModel) {
    val navController = rememberNavController()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Welcome to NewSheet", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Navigate to CharacterCreationScreen */ }) {
            Text("Create New Character")
        }
    }
}