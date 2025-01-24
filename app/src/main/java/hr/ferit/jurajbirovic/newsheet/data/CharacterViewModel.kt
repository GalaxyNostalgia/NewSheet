package hr.ferit.jurajbirovic.newsheet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import hr.ferit.jurajbirovic.newsheet.data.Character
import hr.ferit.jurajbirovic.newsheet.data.Stats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _characterList = MutableStateFlow<List<Character>>(emptyList())
    val characterList: StateFlow<List<Character>> = _characterList
    val currentCharacter = MutableStateFlow(Character("", "", "", "", "", "", "", Stats(0, 0, 0)))

    init {
        loadCharacters()
    }

    fun saveCharacter() {
        val character = currentCharacter.value
        db.collection("characters").document(character.id).set(character)
    }

    fun loadCharacters() {
        db.collection("characters").get().addOnSuccessListener { result ->
            val characters = result.map { document ->
                document.toObject(Character::class.java)
            }
            _characterList.value = characters
        }
    }

    fun deleteCharacter(characterId: String) {
        db.collection("characters").document(characterId).delete()
        loadCharacters()
    }
}