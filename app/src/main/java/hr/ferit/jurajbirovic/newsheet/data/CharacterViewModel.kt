package hr.ferit.jurajbirovic.newsheet.viewmodel

import androidx.lifecycle.ViewModel
import hr.ferit.jurajbirovic.newsheet.data.Character
import hr.ferit.jurajbirovic.newsheet.data.Stats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharacterViewModel : ViewModel() {
    private val _characterList = MutableStateFlow<List<Character>>(emptyList())
    val characterList: StateFlow<List<Character>> get() = _characterList

    init {
        // Add sample characters
        val sampleCharacters = listOf(
            Character(id = "1", name = "Aragorn", title = "Ranger", sex = "Male", race = "Human", characterClass = "Warrior", lore = "A skilled ranger and warrior.", stats = Stats(10, 8, 7)),
            Character(id = "2", name = "Legolas", title = "Archer", sex = "Male", race = "Elf", characterClass = "Archer", lore = "An elf with unmatched archery skills.", stats = Stats(7, 5, 10)),
            Character(id = "3", name = "Gandalf", title = "Wizard", sex = "Male", race = "Maia", characterClass = "Wizard", lore = "A powerful wizard and leader.", stats = Stats(6, 6, 9))
        )
        _characterList.value = sampleCharacters
    }

    fun addCharacter(character: Character) {
        val updatedList = _characterList.value.toMutableList()
        updatedList.add(character)
        _characterList.value = updatedList
    }

    fun saveCharacter(updatedCharacter: Character) {
        val updatedList = _characterList.value.map {
            if (it.id == updatedCharacter.id) updatedCharacter else it
        }
        _characterList.value = updatedList
    }

    fun deleteCharacter(characterId: String) {
        val updatedList = _characterList.value.filter { it.id != characterId }
        _characterList.value = updatedList
    }

}

