package com.nisum.randomusersapp.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nisum.randomusersapp.model.User
import com.nisum.randomusersapp.viewModel.SharedViewModel

@Composable
fun UserList(
    users: List<User>,
    sharedViewModel: SharedViewModel,
    navController: NavHostController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(users) { user ->
            UserCard(user) {
                sharedViewModel.selectedUser = user
                navController.navigate("detailsScreen")
            }
        }
    }
}
