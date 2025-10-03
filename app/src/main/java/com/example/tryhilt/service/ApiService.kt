package com.example.tryhilt.service


import com.example.tryhilt.model.TaskDTO

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService{

    @FormUrlEncoded
    @POST("task.php")
    fun getTask(
        @Field("session_user_id") session_user_id: String,
        @Field("task_platform") task_platform: String
    ): retrofit2.Call<TaskDTO>

}