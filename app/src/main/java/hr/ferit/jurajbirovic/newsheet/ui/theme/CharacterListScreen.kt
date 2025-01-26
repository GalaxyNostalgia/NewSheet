import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CharacterListScreen(
    characterViewModel: CharacterViewModel,
    onAddCharacter: () -> Unit,
    onCharacterSelected: (String) -> Unit
) {
    val characters = characterViewModel.characterList.collectAsState().value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddCharacter) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Character")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)) {
            Text(
                text = "Character List",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (characters.isEmpty()) {
                Text("No Characters Found", style = MaterialTheme.typography.bodyLarge)
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(characters) { character ->
                        Card(
                            onClick = { onCharacterSelected(character.id) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Row(modifier = Modifier.padding(16.dp)) {
                                // Placeholder for character thumbnail/icon
                                Box(modifier = Modifier
                                    .size(40.dp)
                                    .background(MaterialTheme.colorScheme.primary))

                                Spacer(modifier = Modifier.width(16.dp))

                                Column {
                                    Text(text = character.name, style = MaterialTheme.typography.bodyLarge)
                                    Text(text = character.characterClass, style = MaterialTheme.typography.bodyMedium)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}