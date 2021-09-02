package com.example.layarfilm.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.layarfilm.data.NewUpload
import com.example.layarfilm.data.NewUploadList
import com.example.layarfilm.data.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val listNewUpload = MutableLiveData<ArrayList<NewUpload>>()

    fun setNewUpload() {
        RetrofitConfig.instance.getNewUpload()
            .enqueue(
                object : Callback<NewUploadList> {
                    override fun onResponse(
                        call: Call<NewUploadList>,
                        response: Response<NewUploadList>
                    ) {
                        listNewUpload.postValue(response.body()?.result)
                    }

                    override fun onFailure(call: Call<NewUploadList>, t: Throwable) {
                        Log.d("onFailure: ", t.message.toString())
                    }
                })
    }

    fun getNewUpload(): LiveData<ArrayList<NewUpload>> {
        return listNewUpload
    }
}
