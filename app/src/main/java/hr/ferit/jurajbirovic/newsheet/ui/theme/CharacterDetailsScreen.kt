import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import hr.ferit.jurajbirovic.newsheet.data.Stats

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

        Text(text = "Name: ${name.value}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Title: ${title.value}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Sex: ${sex.value}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Race: ${race.value}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Class: ${characterClass.value}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Lore: ${lore.value}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Strength: ${strength.value}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Defense: ${defense.value}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Agility: ${agility.value}", style = MaterialTheme.typography.bodyLarge)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            Button(onClick = {
                characterViewModel.deleteCharacter(characterId)
                onBack()
            }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
                Text("Delete")
            }

            Button(onClick = onBack) {
                Text("Back")
            }
        }
    }
}