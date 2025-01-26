import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import hr.ferit.jurajbirovic.newsheet.data.Character

class CharacterViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _characterList = MutableStateFlow<List<Character>>(emptyList())
    val characterList: StateFlow<List<Character>> get() = _characterList

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        db.collection("characters").get()
            .addOnSuccessListener { result ->
                val characters = result.map { document ->
                    document.toObject(Character::class.java)
                }
                _characterList.value = characters
            }
    }

    fun addCharacter(character: Character) {
        db.collection("characters").add(character)
            .addOnSuccessListener {
                fetchCharacters()
            }
    }

    fun deleteCharacter(characterId: String) {
        db.collection("characters").document(characterId).delete()
            .addOnSuccessListener {
                fetchCharacters()
            }
    }
}