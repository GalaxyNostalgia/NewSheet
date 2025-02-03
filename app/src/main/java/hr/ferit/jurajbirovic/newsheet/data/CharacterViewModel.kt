package hr.ferit.jurajbirovic.newsheet.data

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharacterViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _characterList = MutableStateFlow<List<Character>>(emptyList())
    val characterList: StateFlow<List<Character>> get() = _characterList

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        db.collection("characters").addSnapshotListener { snapshot, e ->
            if (e != null) {
                println("Error fetching characters: ${e.message}")
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val characters = snapshot.map { document ->
                    document.toObject(Character::class.java)
                }
                _characterList.value = characters
            }
        }
    }

    fun addCharacter(character: Character) {
        val docRef = db.collection("characters").document()
        val characterWithId = character.copy(id = docRef.id)
        docRef.set(characterWithId)
            .addOnSuccessListener {
                fetchCharacters()
            }
            .addOnFailureListener { e ->
                println("Error adding character: ${e.message}")
            }
    }

    fun deleteCharacter(characterId: String) {
        db.collection("characters").document(characterId).delete()
            .addOnSuccessListener {
                fetchCharacters()
            }
            .addOnFailureListener { e ->
                println("Error deleting character: ${e.message}")
            }
    }

    fun getCharacterById(characterId: String): Character? {
        return _characterList.value.find { it.id == characterId }
    }
}