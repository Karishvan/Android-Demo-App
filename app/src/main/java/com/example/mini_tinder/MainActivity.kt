package com.example.mini_tinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.mini_tinder.ui.theme.MiniTinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniTinderTheme {
                TinderSwipeScreen()
            }
        }
    }
}

@Composable
fun TinderSwipeScreen(viewModel: UserViewModel = viewModel()) {
    val user = viewModel.currentUser.value
    val isLoading = viewModel.isLoading.value

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else if (user != null) {
            UserCard(user)
        }
        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Button(onClick = {viewModel.showNextUser()}) {
                Text(text = "Pass")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {viewModel.showNextUser()}) {
                Text(text = "Like")
            }
        }
    }
}

@Composable
fun UserCard(user: UserData)  {

    Card(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = user.picture.large,
                contentDescription = "User Photo",
                modifier = Modifier.size(80.dp)
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = user.name.first + " " + user.name.last)
                Text(text = user.location.city + ", " + user.location.state + ", " + user.location.country)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiniTinderTheme {
        TinderSwipeScreen()
    }
}