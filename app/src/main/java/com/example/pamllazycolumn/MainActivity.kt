package com.example.pamllazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavHost
import androidx.navigation.navArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pamllazycolumn.ui.navigation.Screen
import com.example.pamllazycolumn.ui.screen.UserDetailScreen
import com.example.pamllazycolumn.ui.screen.UserListScreen
import com.example.pamllazycolumn.ui.theme.PamlLazyColumnTheme
import com.example.pamllazycolumn.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PamlLazyColumnTheme {
                val navController = rememberNavController()
                val viewModel: UserViewModel = viewModel()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(
                        navController = navController,
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: UserViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.UserList.route,
        modifier = modifier
    ) {
        composable(Screen.UserList.route) {
            UserListScreen(viewModel = viewModel, navController = navController)
        }
        composable(
            route = Screen.UserDetail.route,
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: return@composable
            UserDetailScreen(viewModel = viewModel, userId = userId)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    PamlLazyColumnTheme {
        val navController = rememberNavController()
        val viewModel: UserViewModel = viewModel()
        AppNavHost(navController = navController, viewModel = viewModel)
    }
}
