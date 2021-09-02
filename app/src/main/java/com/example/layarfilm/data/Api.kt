package com.example.layarfilm.data

import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("newupload")
    fun getNewUpload(): Call<NewUploadList>
}