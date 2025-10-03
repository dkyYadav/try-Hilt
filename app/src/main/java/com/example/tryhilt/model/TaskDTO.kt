package com.example.tryhilt.model

data class TaskDTO(
    val count: Int,
    val `data`: List<Task>,
    val status: String
)