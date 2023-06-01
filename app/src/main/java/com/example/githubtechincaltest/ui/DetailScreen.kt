package com.example.githubtechincaltest.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.githubtechincaltest.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel,
    userId: String,
    modifier: Modifier
) {
    val state by remember { detailViewModel.state }

    LaunchedEffect(Unit) {
        detailViewModel.getUser(userId)
    }

    Column(modifier = modifier) {
        AsyncImage(model = state.user?.avatarUrl, contentDescription = null)
        Text(text = "User: ${state.user?.login}")
        Text(text = "Name: ${state.user?.name}")
    }
}

