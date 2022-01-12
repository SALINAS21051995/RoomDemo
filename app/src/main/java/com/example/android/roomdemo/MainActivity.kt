package com.example.android.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android.roomdemo.databinding.ActivityMainBinding
import com.example.android.roomdemo.db.SubscriberDatabase
import com.example.android.roomdemo.db.SubscriberRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: SubscriberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[SubscriberViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        displaySubscriberList()
    }

    private fun displaySubscriberList(){
        viewModel.subscribers.observe(this, {
            Log.i("MyTag", it.toString())
        })
    }
}