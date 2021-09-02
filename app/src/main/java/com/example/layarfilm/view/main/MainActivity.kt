package com.example.layarfilm.view.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.layarfilm.adapter.NewUploadAdapter
import com.example.layarfilm.data.NewUpload
import com.example.layarfilm.data.NewUploadList
import com.example.layarfilm.data.RetrofitConfig
import com.example.layarfilm.databinding.ActivityMainBinding
import com.example.layarfilm.model.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val listNewUpload = ArrayList<NewUpload>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        val adapter = NewUploadAdapter()

        binding.rvNewUpload.setHasFixedSize(true)
        binding.rvNewUpload.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)
        viewModel.getNewUpload().observe(this, {
            adapter.setMovie(it)
            showLoading(false)
            binding.apply {
                rvNewUpload.setHasFixedSize(true)
                rvNewUpload.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                rvNewUpload.adapter = adapter
            }
        })
        viewModel.setNewUpload()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}