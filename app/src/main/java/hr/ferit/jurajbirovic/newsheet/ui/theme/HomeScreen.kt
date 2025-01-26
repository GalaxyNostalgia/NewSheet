package hr.ferit.jurajbirovic.newsheet.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hr.ferit.jurajbirovic.newsheet.viewmodel.CharacterViewModel

@Composable
fun HomeScreen(
    characterViewModel: CharacterViewModel,
    onCharacterSelected: (String) -> Unit,
    onAddCharacter: () -> Unit
) {
    val characters = characterViewModel.characterList.value

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Characters",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(characters.size) { index ->
                val character = characters[index]
                Card(
                    onClick = { onCharacterSelected(character.id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        text = "${character.name} - ${character.title}",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }

        Button(
            onClick = onAddCharacter,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Add New Character")
        }
    }
}
