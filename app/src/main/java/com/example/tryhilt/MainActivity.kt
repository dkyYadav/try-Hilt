package com.example.tryhilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tryhilt.ui.theme.TryHiltTheme
import com.example.tryhilt.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskScreen(sessionId = "abc123", platform = "Playstore")
        }
    }
}

@Composable
fun TaskScreen(
    sessionId: String,
    platform: String,
    viewModel: TaskViewModel = hiltViewModel()
) {
    val tasks by viewModel.tasks.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(true) {
        viewModel.loadTasks(sessionId, platform)
    }

    if (error != null) {
        Text("Error: $error")
    } else {
        LazyColumn {
            items(tasks) { task ->
                Card(Modifier.padding(8.dp)) {
                    Column(Modifier.padding(16.dp)) {
                        Text(text = task.title, style = MaterialTheme.typography.titleMedium)
                        Text(text = task.description)
                        Text(text = "Reward: ${task.reward}")
                    }
                }
            }
        }
    }
}
