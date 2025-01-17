package hr.ferit.jurajbirovic.newsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hr.ferit.jurajbirovic.newsheet.ui.theme.NewSheetTheme
import hr.ferit.jurajbirovic.newsheet.ui.CharacterCreationScreen
import hr.ferit.jurajbirovic.newsheet.viewmodel.CharacterViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import hr.ferit.jurajbirovic.newsheet.ui.theme.NewSheetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewSheetTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val characterViewModel: CharacterViewModel = viewModel()
                    CharacterCreationScreen(characterViewModel = characterViewModel)
                }
            }
        }
    }
}
