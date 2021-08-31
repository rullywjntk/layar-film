package com.example.layarfilm.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {

    @GET("https://api-lk21.herokuapp.com/newupload")
    fun getUpload(
        @Path("result") newUpload: String
    ):Call<NewUploadList>

}