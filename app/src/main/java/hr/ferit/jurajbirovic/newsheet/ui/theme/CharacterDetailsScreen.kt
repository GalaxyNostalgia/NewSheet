package hr.ferit.jurajbirovic.newsheet.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import hr.ferit.jurajbirovic.newsheet.viewmodel.CharacterViewModel

@Composable
fun CharacterDetailsScreen(
    characterId: String,
    characterViewModel: CharacterViewModel,
    navController: NavHostController
) {
    val character = characterViewModel.characterList.value.find { it.id == characterId }

    LaunchedEffect(characterId) {
        character?.let {
            characterViewModel.currentCharacter.value = it
        }
    }

    character?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Name: ${it.name}", style = MaterialTheme.typography.bodyLarge)
            Text("Title: ${it.title}", style = MaterialTheme.typography.bodyLarge)
            Text("Sex: ${it.sex}", style = MaterialTheme.typography.bodyLarge)
            Text("Race: ${it.race}", style = MaterialTheme.typography.bodyLarge)
            Text("Class: ${it.characterClass}", style = MaterialTheme.typography.bodyLarge)
            Text("Lore: ${it.lore}", style = MaterialTheme.typography.bodyLarge)
            Text("Strength: ${it.stats.strength}", style = MaterialTheme.typography.bodyLarge)
            Text("Defense: ${it.stats.defense}", style = MaterialTheme.typography.bodyLarge)
            Text("Agility: ${it.stats.agility}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                characterViewModel.deleteCharacter(characterId)
                navController.popBackStack()
            }) {
                Text("Delete Character")
            }
        }
    }
}