package hr.ferit.jurajbirovic.newsheet.viewmodel

import androidx.lifecycle.ViewModel
import hr.ferit.jurajbirovic.newsheet.data.Character
import com.google.firebase.firestore.FirebaseFirestore
import hr.ferit.jurajbirovic.newsheet.data.Stats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharacterViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private val _characterList = MutableStateFlow<List<Character>>(emptyList())
    val characterList: StateFlow<List<Character>> = _characterList

    var currentCharacter = MutableStateFlow(Character(stats = Stats()))
        private set

    fun saveCharacter(updatedCharacter: Character) {
        val character = currentCharacter.value
        val newCharacterRef = db.collection("characters").document()
        val characterWithId = character.copy(id = newCharacterRef.id)

        newCharacterRef.set(characterWithId)
            .addOnSuccessListener {
                println("Character added successfully.")
                loadCharacters() // Refresh list after adding
            }
            .addOnFailureListener { e ->
                // Handle error
                println("Error adding character: ${e.message}")
            }
    }

    fun loadCharacters() {
        db.collection("characters").get()
            .addOnSuccessListener { result ->
                val characters = result.map { document ->
                    document.toObject(Character::class.java)
                }
                _characterList.value = characters
                println("Characters loaded successfully.")
            }
            .addOnFailureListener { e ->
                // Handle error
                println("Error fetching characters: ${e.message}")
            }
    }

    fun updateCharacterField(field: String, value: Any) {
        currentCharacter.value = when (field) {
            "name" -> currentCharacter.value.copy(name = value as String)
            "title" -> currentCharacter.value.copy(title = value as String)
            "sex" -> currentCharacter.value.copy(sex = value as String)
            "race" -> currentCharacter.value.copy(race = value as String)
            "characterClass" -> currentCharacter.value.copy(characterClass = value as String)
            "lore" -> currentCharacter.value.copy(lore = value as String)
            else -> currentCharacter.value
        }
    }

    fun updateStats(stats: Stats) {
        currentCharacter.value = currentCharacter.value.copy(stats = stats)
    }

    fun deleteCharacter(characterId: String) {
        db.collection("characters").document(characterId)
            .delete()
            .addOnSuccessListener {
                println("Character deleted successfully.")
                loadCharacters() // Refresh list after deletion
            }
            .addOnFailureListener { e ->
                println("Error deleting character: ${e.message}")
            }
    }

    init {
        loadCharacters()
    }
}
