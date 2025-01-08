package com.nisum.randomusersapp.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nisum.randomusersapp.viewModel.SharedViewModel
import com.nisum.randomusersapp.viewModel.UserViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainContent(sharedViewModel: SharedViewModel, userViewModel: UserViewModel) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        val navController = rememberNavController()

        NavHost(navController, startDestination = "mainScreen") {
            composable("mainScreen") {
                MainScreen(navController, sharedViewModel, userViewModel)
            }
            composable("detailsScreen") {
                DetailsScreen(navController, sharedViewModel)
            }
        }
    }
}