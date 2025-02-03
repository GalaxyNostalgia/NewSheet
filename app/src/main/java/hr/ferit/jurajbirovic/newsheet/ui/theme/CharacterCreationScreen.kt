package hr.ferit.jurajbirovic.newsheet.ui.theme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.ferit.jurajbirovic.newsheet.data.Character
import hr.ferit.jurajbirovic.newsheet.data.CharacterViewModel
import hr.ferit.jurajbirovic.newsheet.data.Stats
import kotlinx.coroutines.delay

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
    val strength = remember { mutableIntStateOf(0) }
    val defense = remember { mutableIntStateOf(0) }
    val agility = remember { mutableIntStateOf(0) }
    val visible = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300)
        visible.value = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Create Character",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        )

        AnimatedVisibility(
            visible = visible.value,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { it / 2 })
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    label = { Text("Name") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    label = { Text("Title") },
                    leadingIcon = { Icon(Icons.Default.Title, contentDescription = "Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = sex.value,
                    onValueChange = { sex.value = it },
                    label = { Text("Sex") },
                    leadingIcon = { Icon(Icons.Default.Wc, contentDescription = "Sex") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = race.value,
                    onValueChange = { race.value = it },
                    label = { Text("Race") },
                    leadingIcon = { Icon(Icons.Default.Flag, contentDescription = "Race") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = characterClass.value,
                    onValueChange = { characterClass.value = it },
                    label = { Text("Class") },
                    leadingIcon = { Icon(Icons.Default.WorkspacePremium, contentDescription = "Class") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = lore.value,
                    onValueChange = { lore.value = it },
                    label = { Text("Lore") },
                    leadingIcon = { Icon(Icons.Default.Book, contentDescription = "Lore") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = strength.intValue.toString(),
                        onValueChange = { strength.intValue = it.toIntOrNull() ?: 0 },
                        label = { Text("Strength") },
                        leadingIcon = { Icon(Icons.Default.FitnessCenter, contentDescription = "Strength") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = defense.intValue.toString(),
                        onValueChange = { defense.intValue = it.toIntOrNull() ?: 0 },
                        label = { Text("Defense") },
                        leadingIcon = { Icon(Icons.Default.Shield, contentDescription = "Defense") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = agility.intValue.toString(),
                        onValueChange = { agility.intValue = it.toIntOrNull() ?: 0 },
                        label = { Text("Agility") },
                        leadingIcon = { Icon(Icons.AutoMirrored.Filled.DirectionsRun, contentDescription = "Agility") },
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            val newCharacter = Character(
                                id = System.currentTimeMillis().toString(),
                                name = name.value,
                                title = title.value,
                                sex = sex.value,
                                race = race.value,
                                characterClass = characterClass.value,
                                lore = lore.value,
                                stats = Stats(strength.intValue, defense.intValue, agility.intValue)
                            )
                            characterViewModel.addCharacter(newCharacter)
                            onSave()
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Save")
                    }

                    Button(
                        onClick = onCancel,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                    ) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}