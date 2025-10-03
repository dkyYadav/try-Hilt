package com.example.tryhilt.model

data class TaskResponseDto(
    val status: String,
    val count: Int,
    val data: List<TaskDto>
)

data class TaskDto(
    val task_id: String,
    val task_platform: String,
    val task_title: String,
    val task_description: String,
    val task_icon: String,
    val task_reward: String,
    val task_button_text: String,
    val task_link: String,
    val created_at: String
)

fun TaskDto.toTask(): Task = Task(
    id = task_id,
    platform = task_platform,
    title = task_title,
    description = task_description,
    icon = task_icon,
    reward = task_reward,
    buttonText = task_button_text,
    link = task_link,
    createdAt = created_at
)
