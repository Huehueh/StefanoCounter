package eu.kinol.stefanocounter.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import eu.kinol.stefanocounter.data.ProfileScreenState
import eu.kinol.stefanocounter.profile4Week
import eu.kinol.stefanocounter.ui.MainContent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    profileData: ProfileScreenState,
    navigateToEditScreen:()->Unit
) {
    Scaffold(
        topBar = {
            MainAppBar(
                profileData = profileData,
                onEditClicked = navigateToEditScreen
            )
        }
    )
    { padding ->
        Box(modifier = Modifier.padding(padding)) {
            MainContent(profileData = profileData)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(
    profileData: ProfileScreenState,
    onEditClicked:()->Unit
) {
    TopAppBar(
        title = {
            Text(text = profileData.name ?: "Child")
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.secondary),
        actions = {
            IconButton(onClick = onEditClicked) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    )
}

@Preview
@Composable
fun MainAppBarPreview() {
    MainAppBar(profileData = profile4Week) {

    }
}


