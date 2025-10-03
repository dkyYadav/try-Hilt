package com.example.tryhilt.model

import com.google.gson.annotations.SerializedName

data class Data(
    val created_at: String,
    val task_button_text: String,
    val task_description: String,
    val task_icon: String,
    val task_id: String,
    val task_link: String,
    val task_platform: String,
    val task_reward: String,
    val task_title: String
)
data class Task(
    @SerializedName("task_id") val taskId: String,
    @SerializedName("task_platform") val taskPlatform: String,
    @SerializedName("task_title") val taskTitle: String,
    @SerializedName("task_description") val taskDescription: String,
    @SerializedName("task_icon") val taskIcon: String,
    @SerializedName("task_reward") val taskReward: String,
    @SerializedName("task_button_text") val taskButtonText: String,
    @SerializedName("task_link") val taskLink: String,
    @SerializedName("created_at") val createdAt: String
)
