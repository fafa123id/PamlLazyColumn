package com.example.pamllazycolumn.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.pamllazycolumn.viewmodel.UserViewModel
import com.example.pamllazycolumn.ui.navigation.Screen

@Composable
fun UserListScreen(viewModel: UserViewModel, navController: NavHostController) {
    val users by viewModel.users.collectAsState()

    LazyColumn {
        items(users) { user ->
            ListItem(
                headlineContent = { Text(user.name) },
                supportingContent = { Text(user.email) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(Screen.UserDetail.createRoute(user.id))
                    }
            )
            Divider()
        }
    }
}
