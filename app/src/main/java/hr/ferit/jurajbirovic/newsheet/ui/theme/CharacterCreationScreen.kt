import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hr.ferit.jurajbirovic.newsheet.data.Character
import hr.ferit.jurajbirovic.newsheet.data.Stats

@Composable
fun CharacterCreationScreen(
    characterViewModel: CharacterViewModel,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    val name = remember { mutableStateOf("") }
    val title = remember { mutableStateOf("") }
    val sex = remember { mutableStateOf("") }
    val race = remember { mutableStateOf("") }
    val characterClass = remember { mutableStateOf("") }
    val lore = remember { mutableStateOf("") }
    val strength = remember { mutableStateOf(0) }
    val defense = remember { mutableStateOf(0) }
    val agility = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Create Character", style = MaterialTheme.typography.headlineLarge)

        OutlinedTextField(value = name.value, onValueChange = { name.value = it }, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = title.value, onValueChange = { title.value = it }, label = { Text("Title") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = sex.value, onValueChange = { sex.value = it }, label = { Text("Sex") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = race.value, onValueChange = { race.value = it }, label = { Text("Race") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = characterClass.value, onValueChange = { characterClass.value = it }, label = { Text("Class") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = lore.value, onValueChange = { lore.value = it }, label = { Text("Lore") }, modifier = Modifier.fillMaxWidth())

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(value = strength.value.toString(), onValueChange = { strength.value = it.toIntOrNull() ?: 0 }, label = { Text("Strength") }, modifier = Modifier.weight(1f))
            OutlinedTextField(value = defense.value.toString(), onValueChange = { defense.value = it.toIntOrNull() ?: 0 }, label = { Text("Defense") }, modifier = Modifier.weight(1f))
            OutlinedTextField(value = agility.value.toString(), onValueChange = { agility.value = it.toIntOrNull() ?: 0 }, label = { Text("Agility") }, modifier = Modifier.weight(1f))
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                val newCharacter = Character(
                    name = name.value,
                    title = title.value,
                    sex = sex.value,
                    race = race.value,
                    characterClass = characterClass.value,
                    lore = lore.value,
                    stats = Stats(strength.value, defense.value, agility.value)
                )
                characterViewModel.addCharacter(newCharacter)
                onSave() // Navigate back to the list screen
            }) {
                Text("Save")
            }

            Button(onClick = onCancel, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
                Text("Cancel")
            }
        }
    }
}