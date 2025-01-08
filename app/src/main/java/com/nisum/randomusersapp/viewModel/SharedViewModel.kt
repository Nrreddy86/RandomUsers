package com.nisum.randomusersapp.viewModel

import androidx.lifecycle.ViewModel
import com.nisum.randomusersapp.model.User

class SharedViewModel : ViewModel() {
    var selectedUser: User? = null
}