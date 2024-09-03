package eu.kinol.stefanocounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import eu.kinol.stefanocounter.ui.theme.StefanoCounterTheme
import androidx.activity.viewModels
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.IconButton
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.kinol.stefanocounter.data.CalculationDate
import eu.kinol.stefanocounter.data.ProfileViewModel
import eu.kinol.stefanocounter.navigation.editProfileComposable
import eu.kinol.stefanocounter.navigation.mainComposable
import java.time.LocalDate

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel : ProfileViewModel by viewModels()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StefanoCounterTheme {
                // A surface container using the 'background' color from the theme
                navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "MAIN"
                ) {
                    mainComposable(
                        navigateToEditScreen = {
                            navController.navigate("EDIT")
                        },
                        viewModel = viewModel
                    )
                    editProfileComposable(
                        navigateToMainScreen = {
                            navController.navigate("MAIN")
                        },
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}










