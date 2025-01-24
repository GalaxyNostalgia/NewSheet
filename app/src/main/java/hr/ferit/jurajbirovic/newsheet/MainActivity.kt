package hr.ferit.jurajbirovic.newsheet

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import hr.ferit.jurajbirovic.newsheet.ui.CharacterCreationScreen
import hr.ferit.jurajbirovic.newsheet.ui.CharacterDetailsScreen
import hr.ferit.jurajbirovic.newsheet.ui.CharacterListScreen
import hr.ferit.jurajbirovic.newsheet.ui.theme.NewSheetTheme
import hr.ferit.jurajbirovic.newsheet.viewmodel.CharacterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewSheetTheme {
                val navController = rememberNavController()
                val characterViewModel: CharacterViewModel = viewModel()
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavHost(navController = navController, startDestination = "character_list") {
                        composable("character_list") {
                            CharacterListScreen(
                                characterViewModel = characterViewModel,
                                onAddCharacter = { navController.navigate("character_creation") },
                                onCharacterSelected = { characterId ->
                                    navController.navigate("character_details/$characterId")
                                }
                            )
                        }
                        composable("character_creation") {
                            CharacterCreationScreen(
                                characterViewModel = characterViewModel,
                                onSave = { navController.popBackStack() },
                                onCancel = { navController.popBackStack() }
                            )
                        }
                        composable("character_details/{characterId}") { backStackEntry ->
                            val characterId = backStackEntry.arguments?.getString("characterId") ?: ""
                            CharacterDetailsScreen(
                                characterId = characterId,
                                characterViewModel = characterViewModel,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
        val firebaseApp = FirebaseApp.getInstance()
        if (firebaseApp != null) {
            Log.d("MainActivity", "Firebase is initialized successfully.")
        } else {
            Log.e("MainActivity", "Firebase is not initialized.")
        }
    }
}