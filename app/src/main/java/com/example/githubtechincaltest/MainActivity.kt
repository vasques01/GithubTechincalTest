package com.example.githubtechincaltest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.githubtechincaltest.navigation.Navigation
import com.example.githubtechincaltest.theme.GithubProjectTheme
import com.example.githubtechincaltest.viewmodel.DetailViewModel
import com.example.githubtechincaltest.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GithubUserSearchApp(viewModel = viewModel, detailViewModel = detailViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubUserSearchApp(viewModel: MainViewModel, detailViewModel: DetailViewModel) {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Github User Search")})
        }
    ) {
        val navHostController = rememberNavController()
        Navigation(
            navHostController = navHostController,
            mainViewModel = viewModel,
            detailViewModel = detailViewModel,
            paddingValues = it
        )
    }
}