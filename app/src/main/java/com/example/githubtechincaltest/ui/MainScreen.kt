package com.example.githubtechincaltest

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.githubtechincaltest.data.model.User
import com.example.githubtechincaltest.viewmodel.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onUserClicked: (String) -> Unit,
    modifier: Modifier
) {
    val state by remember { viewModel.state }
    var query by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        //Search Box
        TextField(
            value = query,
            onValueChange = {
                query = it
                viewModel.searchUsers(it)
            },
            label = { Text(text = "Search by username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        //Users List
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(state.users) { index, user ->
                DisplayUserItem(user = user, onUserClicked = onUserClicked)

                if (index == state.users.size - 1 && !state.isLoading && !state.error && !state.isSearching) {
                    viewModel.loadMoreData()
                }
            }

            if (state.isLoading) {
                item {
                    CircularProgressIndicator()
                }
            }

            if (state.error) {
                item {
                    Text(
                        text = "Error fetching items",
                        color = Color.Red,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun DisplayUserItem(user: User, onUserClicked: (String) -> Unit) {
    Text(text = user.login, modifier = Modifier.padding(16.dp).clickable {
        onUserClicked(user.login)
    })
}