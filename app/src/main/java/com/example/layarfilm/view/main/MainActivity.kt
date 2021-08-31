package com.example.layarfilm.view.main

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.layarfilm.adapter.NewUploadAdapter
import com.example.layarfilm.databinding.ActivityMainBinding
import com.example.layarfilm.model.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NewUploadAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        adapter = NewUploadAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getNewUpload().observe(this, {
            if (it != null) {
                adapter.setData(it)
                showLoading(false)
            }
        })

        binding.apply {
            rvNewUpload.layoutManager =LinearLayoutManager(this@MainActivity)
            rvNewUpload.setHasFixedSize(true)
            rvNewUpload.adapter = adapter
            etSearch.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val search =etSearch.text.toString()
                    showLoading(true)
                    viewModel.setNewUpload(search)
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}