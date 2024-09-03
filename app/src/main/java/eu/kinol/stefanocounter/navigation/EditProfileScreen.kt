package eu.kinol.stefanocounter.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import eu.kinol.stefanocounter.data.CalculationDate
import eu.kinol.stefanocounter.data.ProfileScreenState
import eu.kinol.stefanocounter.data.ProfileViewModel
import eu.kinol.stefanocounter.ui.EditProfileContent
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navigateToEditScreen:()->Unit,
    viewModel: ProfileViewModel
) {
    Scaffold(
        topBar = {
            EditAppBar(
                onSaveClicked = navigateToEditScreen
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            EditProfileContent(
                profileName = "",
                onProfileNameChange = {},
                calculationDate = CalculationDate.BIRTH,
                onCalculationDateChange = {},
                date = LocalDate.now(),
                onDateChange = {}
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAppBar(
    onSaveClicked:()->Unit
) {
    TopAppBar(
        title = {
            Text(text = "Edit profile")
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.secondary),
        actions = {
            IconButton(onClick = onSaveClicked) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Save",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    )
}

@Preview
@Composable
fun EditAppBarPreview() {
    EditAppBar {

    }
}