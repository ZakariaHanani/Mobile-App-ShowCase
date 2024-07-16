package com.valasapplication.app

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("signup.php") // Change to your PHP endpoint URL
    fun checkEmailDuplicate(@Field("email") email: String): Call<Boolean>
}
