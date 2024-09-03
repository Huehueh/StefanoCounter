package eu.kinol.stefanocounter.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import eu.kinol.stefanocounter.data.ProfileViewModel
import eu.kinol.stefanocounter.profile4Week

fun NavGraphBuilder.mainComposable(
    navigateToEditScreen:()->Unit,
    viewModel:ProfileViewModel
) {
    composable(route = "MAIN")
    {
        MainScreen(
            profileData = profile4Week,
            navigateToEditScreen
        )
    }
}

fun NavGraphBuilder.editProfileComposable(
    navigateToMainScreen:()->Unit,
    viewModel: ProfileViewModel
) {
    composable(route = "EDIT")
    {
        EditProfileScreen(
            navigateToEditScreen = navigateToMainScreen,
            viewModel = viewModel
        )
    }
}