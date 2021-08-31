package com.example.layarfilm.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api-lk21.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(Service::class.java)
}