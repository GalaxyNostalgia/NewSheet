package hr.ferit.jurajbirovic.newsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hr.ferit.jurajbirovic.newsheet.ui.theme.CharacterCreationScreen
import hr.ferit.jurajbirovic.newsheet.ui.CharacterDetailsScreen
import hr.ferit.jurajbirovic.newsheet.ui.theme.NewSheetTheme
import hr.ferit.jurajbirovic.newsheet.viewmodel.CharacterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewSheetApp()
        }
    }
}

@Composable
fun NewSheetApp() {
    val navController = rememberNavController()
    val characterViewModel: CharacterViewModel = viewModel()

    NewSheetTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavigation(navController, characterViewModel)
        }
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    characterViewModel: CharacterViewModel
) {
    NavHost(navController = navController, startDestination = "character_creation") {
        composable("character_creation") {
            CharacterCreationScreen(
                characterViewModel = characterViewModel,
                onSave = { navController.navigate("character_list") },
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
