package com.example.tryhilt.repository

import com.example.tryhilt.model.Task
import com.example.tryhilt.model.toTask
import com.example.tryhilt.service.ApiService
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : TaskRepository {

    override suspend fun getTasks(session_user_id: String, task_platform: String): List<Task> {
        return apiService.getTask(session_user_id, task_platform).data.map { it.toTask() }
    }
}
