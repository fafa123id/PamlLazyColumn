package com.example.pamllazycolumn.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pamllazycolumn.viewmodel.UserViewModel

@Composable
fun UserDetailScreen(userId: Int, viewModel: UserViewModel) {
    val user by viewModel.selectedUser.collectAsState()

    LaunchedEffect(userId) {
        viewModel.loadUser(userId)
    }

    user?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Name: ${it.name}", style = MaterialTheme.typography.titleLarge)
            Text("Username: ${it.username}")
            Text("Email: ${it.email}")
            Text("Address:", style = MaterialTheme.typography.titleMedium)
            Text("Street: ${it.address.street}")
            Text("Suite: ${it.address.suite}")
            Text("City: ${it.address.city}")
            Text("Zipcode: ${it.address.zipcode}")
            Text("Geo: Lat: ${it.address.geo.lat}, Lng: ${it.address.geo.lng}")
            Text("Phone: ${it.phone}")
            Text("Website: ${it.website}")
            Text("Company:", style = MaterialTheme.typography.titleMedium)
            Text("Name: ${it.company.name}")
            Text("Catchphrase: ${it.company.catchPhrase}")
            Text("Business: ${it.company.bs}")
        }
    } ?: run {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    }
}
