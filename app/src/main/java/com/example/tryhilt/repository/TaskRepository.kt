package com.example.tryhilt.repository

import android.telecom.Call
import com.example.tryhilt.model.Task
import com.example.tryhilt.service.ApiService
import javax.inject.Inject

interface TaskRepository {
   suspend fun getTasks(session_user_id: String, task_platform: String): List<Task>
}