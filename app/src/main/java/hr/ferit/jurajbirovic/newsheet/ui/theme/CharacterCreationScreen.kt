package hr.ferit.jurajbirovic.newsheet.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hr.ferit.jurajbirovic.newsheet.data.Stats
import hr.ferit.jurajbirovic.newsheet.viewmodel.CharacterViewModel

@Composable
fun CharacterCreationScreen(
    characterViewModel: CharacterViewModel,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var sex by remember { mutableStateOf("") }
    var race by remember { mutableStateOf("") }
    var characterClass by remember { mutableStateOf("") }
    var lore by remember { mutableStateOf("") }
    var strength by remember { mutableStateOf(0) }
    var defense by remember { mutableStateOf(0) }
    var agility by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )
        TextField(
            value = sex,
            onValueChange = { sex = it },
            label = { Text("Sex") }
        )
        TextField(
            value = race,
            onValueChange = { race = it },
            label = { Text("Race") }
        )
        TextField(
            value = characterClass,
            onValueChange = { characterClass = it },
            label = { Text("Class") }
        )
        TextField(
            value = lore,
            onValueChange = { lore = it },
            label = { Text("Lore") }
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = strength.toString(),
                onValueChange = { strength = it.toIntOrNull() ?: 0 },
                label = { Text("Strength") },
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = defense.toString(),
                onValueChange = { defense = it.toIntOrNull() ?: 0 },
                label = { Text("Defense") },
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = agility.toString(),
                onValueChange = { agility = it.toIntOrNull() ?: 0 },
                label = { Text("Agility") },
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = {
                characterViewModel.currentCharacter.value = characterViewModel.currentCharacter.value.copy(
                    name = name,
                    title = title,
                    sex = sex,
                    race = race,
                    characterClass = characterClass,
                    lore = lore,
                    stats = Stats(strength, defense, agility)
                )
                characterViewModel.saveCharacter()
                onSave()
            }) {
                Text("Save")
            }
            Button(onClick = onCancel) {
                Text("Cancel")
            }
        }
    }
}