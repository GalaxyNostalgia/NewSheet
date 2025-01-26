import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hr.ferit.jurajbirovic.newsheet.data.Stats
import hr.ferit.jurajbirovic.newsheet.viewmodel.CharacterViewModel
import androidx.compose.runtime.collectAsState

@Composable
fun CharacterDetailsScreen(
    characterId: String,
    characterViewModel: CharacterViewModel,
    onBack: () -> Unit
) {
    val characterList = characterViewModel.characterList.collectAsState().value
    val character = characterList.find { it.id == characterId }

    if (character == null) {
        Text("Character not found.", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = onBack) { Text("Back") }
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
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Character Details", style = MaterialTheme.typography.headlineLarge)

        // Fields for character properties (similar to CharacterCreationScreen)

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
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
                    onBack()
                }
            ) {
                Text("Save")
            }

            Button(
                onClick = {
                    characterViewModel.deleteCharacter(characterId)
                    onBack()
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Delete")
            }
        }
    }
}
