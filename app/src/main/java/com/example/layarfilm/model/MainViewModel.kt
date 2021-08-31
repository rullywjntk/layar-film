package com.example.layarfilm.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.layarfilm.data.NewUpload
import com.example.layarfilm.data.NewUploadList
import com.example.layarfilm.data.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val listNewUpload = MutableLiveData<ArrayList<NewUpload>>()

    fun setNewUpload(newUpload: String) {
        RetrofitConfig.service
            .getUpload(newUpload)
            .enqueue(object : Callback<NewUploadList> {

                override fun onResponse(call: Call<NewUploadList>, response: Response<NewUploadList>) {
                    if (response.isSuccessful) {
                        listNewUpload.postValue(response.body()?.uploadList)
                    }
                }
                override fun onFailure(call: Call<NewUploadList>, t: Throwable) {
                    t.printStackTrace()
                    Log.d("onFailure", t.message.toString())
                }
            })
    }

    fun getNewUpload(): LiveData<ArrayList<NewUpload>> {
        return listNewUpload
    }
}
