package hr.ferit.jurajbirovic.newsheet.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import hr.ferit.jurajbirovic.newsheet.data.Character
import hr.ferit.jurajbirovic.newsheet.viewmodel.CharacterViewModel

@Composable
fun CharacterListScreen(
    characterViewModel: CharacterViewModel = viewModel(),
    onAddCharacter: () -> Unit,
    onCharacterSelected: (String) -> Unit
) {
    val characterList = characterViewModel.characterList.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = onAddCharacter) {
            Text("Add Character")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(characterList) { character ->
                CharacterListItem(character, onCharacterSelected)
            }
        }
    }
}

@Composable
fun CharacterListItem(character: Character, onCharacterSelected: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCharacterSelected(character.id) }
            .padding(8.dp)
    ) {
        Text(character.name, style = MaterialTheme.typography.bodyLarge)
    }
}