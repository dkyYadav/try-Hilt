package com.example.tryhilt.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tryhilt.model.Task
import com.example.tryhilt.model.TaskDTO
import com.example.tryhilt.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject
import retrofit2.Callback

@HiltViewModel
class instagramviewmode @Inject constructor(
    private val repository: TaskRepository
): ViewModel() {


    var taskList by mutableStateOf<List<Task>>(emptyList())
        private set

    var errorMessage by mutableStateOf("")
        private set

    fun fetchTasks(userId: String, taskType: String) {
        repository.getTasks(userId, taskType).enqueue(object : Callback<TaskDTO> {
            override fun onResponse(call: retrofit2.Call<TaskDTO>, response: Response<TaskDTO>) {
                if (response.isSuccessful && response.body()?.status == "success") {
                    val tasks = response.body()?.data ?: emptyList()
                    taskList = tasks

                    // ✅ Log each task
                    tasks.forEachIndexed { index, task ->
                        Log.d("API_TASK", "[$index] ${task.taskTitle} (${task.taskPlatform}) - Reward: ${task.taskReward}")
                    }

                } else {
                    errorMessage = "Error: ${response.errorBody()?.string()}"
                    Log.e("API_ERROR", errorMessage)
                }
            }

            override fun onFailure(call: retrofit2.Call<TaskDTO>, t: Throwable) {
                errorMessage = "Failure: ${t.message}"
                Log.e("API_FAILURE", errorMessage)
            }
        })
    }

}