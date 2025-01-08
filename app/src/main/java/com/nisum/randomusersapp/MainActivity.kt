package com.nisum.randomusersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.nisum.randomusersapp.ui.theme.RandomUsersAppTheme
import com.nisum.randomusersapp.view.MainContent
import com.nisum.randomusersapp.viewModel.SharedViewModel
import com.nisum.randomusersapp.viewModel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val sharedViewModel: SharedViewModel by viewModels()
        val userViewModel: UserViewModel by viewModels()
        setContent {
            RandomUsersAppTheme {
                MainContent(sharedViewModel, userViewModel)
            }
        }
    }
}
