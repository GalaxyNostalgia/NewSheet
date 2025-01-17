package hr.ferit.jurajbirovic.newsheet.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hr.ferit.jurajbirovic.newsheet.data.Character
import hr.ferit.jurajbirovic.newsheet.viewmodel.CharacterViewModel

@Composable
fun CharacterListScreen(
    characterViewModel: CharacterViewModel,
    onCharacterSelected: (Character) -> Unit,
    onCreateNewCharacter: () -> Unit
) {
    val characterList = characterViewModel.characterList.collectAsState().value

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Your Characters",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(characterList) { character ->
                CharacterItem(
                    character = character,
                    onClick = { onCharacterSelected(character) }
                )
            }
        }

        Button(
            onClick = onCreateNewCharacter,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Create New Character")
        }
    }
}

@Composable
fun CharacterItem(character: Character, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = character.name, style = MaterialTheme.typography.titleLarge)
            Text(text = character.title, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

