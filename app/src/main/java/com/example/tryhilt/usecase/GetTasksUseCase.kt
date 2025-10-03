package com.example.tryhilt.usecase

import com.example.tryhilt.model.Task
import com.example.tryhilt.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetTasksUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    operator fun invoke(session_user_id: String,task_platform: String): Flow<Result<List<Task>>> = flow {
        try {
            val result = repository.getTasks(session_user_id, task_platform)
            emit(Result.success(result))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
