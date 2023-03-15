package com.example.networkingpractise

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.networkingpractise.databinding.ActivityMainBinding
import com.example.networkingpractise.utils.NetWorkHelper

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var netWorkHelper: NetWorkHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Faqat dasturga kirganda bir marta tekshiriladi
        if (isNetWorkConnected()) {

            binding.tv.text = "Connected"

        } else {

            binding.tv.text = "Disconnected"

        }

    }

    private fun isNetWorkConnected(): Boolean {

        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork

        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)

        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

    }

}