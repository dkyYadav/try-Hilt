package com.example.tryhilt.repository

import android.telecom.Call
import com.example.tryhilt.model.TaskDTO
import com.example.tryhilt.service.ApiService
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getTasks(userId: String, taskType: String): retrofit2.Call<TaskDTO> {
        return apiService.getTask(userId, taskType)
    }
}