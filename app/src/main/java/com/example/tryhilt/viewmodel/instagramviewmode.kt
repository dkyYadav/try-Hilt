package com.example.tryhilt.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tryhilt.model.Task
import com.example.tryhilt.repository.TaskRepository
import com.example.tryhilt.usecase.GetTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import retrofit2.Callback

/*@HiltViewModel
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
    }*/

    @HiltViewModel
    class TaskViewModel @Inject constructor(
        private val getTasksUseCase: GetTasksUseCase
    ) : ViewModel() {

        private val _tasks = MutableStateFlow<List<Task>>(emptyList())
        val tasks = _tasks.asStateFlow()

        private val _error = MutableStateFlow<String?>(null)
        val error = _error.asStateFlow()

        fun loadTasks(userId: String, taskType: String) {
            viewModelScope.launch {
                getTasksUseCase(userId, taskType).collect { result ->
                    result.onSuccess { _tasks.value = it }
                        .onFailure { _error.value = it.message }
                }
            }
        }
    }

