package com.example.layarfilm.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {

    @GET("newupload")
    fun getUpload(
        @Path("newupload") newupload: String
    ):Call<NewUploadList>

}