package com.nisum.randomusersapp.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nisum.randomusersapp.model.ApiResult
import com.nisum.randomusersapp.model.User
import com.nisum.randomusersapp.viewModel.SharedViewModel
import com.nisum.randomusersapp.viewModel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    userViewModel: UserViewModel
) {

    val usersState by userViewModel.usersState.collectAsState()
    var userCount by remember { mutableStateOf("5") } // Holds the input number
    val keyboardController = LocalSoftwareKeyboardController.current

    var flag = false

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Dynamic User Fetcher") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(8.dp)
        ) {
            // Input Field
            OutlinedTextField(
                value = userCount,
                onValueChange = { userCount = it.filter { char -> char.isDigit() } },
                label = { Text("Enter number of users") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    val count = userCount.toIntOrNull()
                    if (count != null && count > 0) {
                        flag = true
                        userViewModel.fetchUsers(count)
                    }
                    keyboardController?.hide()
                })
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Fetch Button
            Button(
                onClick = {
                    keyboardController?.hide()
                    val count = userCount.toIntOrNull()
                    if (count != null && count > 0) {
                        flag = true
                        userViewModel.fetchUsers(count)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Fetch Users")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // User List or Loading/Error
            Box(modifier = Modifier.fillMaxSize()) {
                when (usersState) {
                    is ApiResult.Loading -> {
                        if (flag)
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentSize()
                            )
                    }

                    is ApiResult.Success -> {
                        UserList(
                            (usersState as ApiResult.Success<List<User>>).data,
                            sharedViewModel,
                            navController
                        )
                    }

                    is ApiResult.Error -> {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(),
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = (usersState as ApiResult.Error).message,
                                color = MaterialTheme.colorScheme.error,
                            )
                        }
                    }
                }
            }
        }
    }
}
