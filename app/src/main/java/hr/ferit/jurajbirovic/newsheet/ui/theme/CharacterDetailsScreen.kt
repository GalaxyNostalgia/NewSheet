package hr.ferit.jurajbirovic.newsheet.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import hr.ferit.jurajbirovic.newsheet.data.Stats
import hr.ferit.jurajbirovic.newsheet.viewmodel.CharacterViewModel

@Composable
fun CharacterDetailsScreen(
    characterId: String,
    characterViewModel: CharacterViewModel,
    navController: NavController
) {
    val character = characterViewModel.characterList.value.find { it.id == characterId }

    if (character == null) {
        Text("Character not found.", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
        return
    }

    val name = remember { mutableStateOf(character.name) }
    val title = remember { mutableStateOf(character.title) }
    val sex = remember { mutableStateOf(character.sex) }
    val race = remember { mutableStateOf(character.race) }
    val characterClass = remember { mutableStateOf(character.characterClass) }
    val lore = remember { mutableStateOf(character.lore) }
    val strength = remember { mutableStateOf(character.stats.strength) }
    val defense = remember { mutableStateOf(character.stats.defense) }
    val agility = remember { mutableStateOf(character.stats.agility) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Character Details", style = MaterialTheme.typography.headlineLarge)

        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = title.value,
            onValueChange = { title.value = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = sex.value,
            onValueChange = { sex.value = it },
            label = { Text("Sex") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = race.value,
            onValueChange = { race.value = it },
            label = { Text("Race") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = characterClass.value,
            onValueChange = { characterClass.value = it },
            label = { Text("Class") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = lore.value,
            onValueChange = { lore.value = it },
            label = { Text("Lore") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = strength.value.toString(),
                onValueChange = { strength.value = it.toIntOrNull() ?: 0 },
                label = { Text("Strength") },
                modifier = Modifier.weight(1f)
            )

            OutlinedTextField(
                value = defense.value.toString(),
                onValueChange = { defense.value = it.toIntOrNull() ?: 0 },
                label = { Text("Defense") },
                modifier = Modifier.weight(1f)
            )

            OutlinedTextField(
                value = agility.value.toString(),
                onValueChange = { agility.value = it.toIntOrNull() ?: 0 },
                label = { Text("Agility") },
                modifier = Modifier.weight(1f)
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = {
                    val updatedCharacter = character.copy(
                        name = name.value,
                        title = title.value,
                        sex = sex.value,
                        race = race.value,
                        characterClass = characterClass.value,
                        lore = lore.value,
                        stats = Stats(
                            strength = strength.value,
                            defense = defense.value,
                            agility = agility.value
                        )
                    )
                    characterViewModel.saveCharacter(updatedCharacter)
                    navController.popBackStack()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Save")
            }

            Button(
                onClick = {
                    characterViewModel.deleteCharacter(characterId)
                    navController.popBackStack()
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Delete")
            }
        }

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Back")
        }
    }
}
