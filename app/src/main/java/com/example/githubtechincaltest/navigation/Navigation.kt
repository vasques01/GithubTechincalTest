package com.example.githubtechincaltest.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.githubtechincaltest.MainScreen
import com.example.githubtechincaltest.theme.GithubProjectTheme
import com.example.githubtechincaltest.ui.DetailScreen
import com.example.githubtechincaltest.viewmodel.DetailViewModel
import com.example.githubtechincaltest.viewmodel.MainViewModel


@Composable
fun Navigation(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    detailViewModel: DetailViewModel,
    paddingValues: PaddingValues
) {
    NavHost(navController = navHostController, startDestination = "users") {
        composable("users") {
            GithubProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(viewModel = mainViewModel, modifier = Modifier.padding(paddingValues), onUserClicked = { id ->
                        navHostController.navigate("userDetails/$id")
                    })
                }
            }
        }

        composable("userDetails/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            GithubProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetailScreen(detailViewModel = detailViewModel, modifier = Modifier.padding(paddingValues), userId = userId)
                }
            }
        }
    }
}