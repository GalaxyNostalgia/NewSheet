package hr.ferit.jurajbirovic.newsheet

import hr.ferit.jurajbirovic.newsheet.ui.theme.CharacterCreationScreen
import hr.ferit.jurajbirovic.newsheet.ui.theme.CharacterListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import hr.ferit.jurajbirovic.newsheet.data.CharacterViewModel
import hr.ferit.jurajbirovic.newsheet.ui.theme.CharacterDetailsScreen
import hr.ferit.jurajbirovic.newsheet.ui.theme.NewSheetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewSheetTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val characterViewModel: CharacterViewModel = viewModel()

    Surface(color = MaterialTheme.colorScheme.background) {
        AppNavigation(navController, characterViewModel)
    }
}

@Composable
fun AppNavigation(navController: NavHostController, characterViewModel: CharacterViewModel) {
    NavHost(
        navController = navController,
        startDestination = "character_list"
    ) {
        composable("character_list") {
            CharacterListScreen(
                characterViewModel = characterViewModel,
                onAddCharacter = {
                    navController.navigate("character_creation")
                },
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
            val characterId = backStackEntry.arguments?.getString("characterId")
            if (characterId != null) {
                val character = characterViewModel.getCharacterById(characterId)
                CharacterDetailsScreen(
                    character = character,
                    characterViewModel = characterViewModel,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}