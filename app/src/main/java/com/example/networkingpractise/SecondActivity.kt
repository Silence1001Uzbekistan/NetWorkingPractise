package com.example.networkingpractise

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.networkingpractise.databinding.ActivitySecondBinding
import com.example.networkingpractise.utils.NetWorkHelper
import org.json.JSONObject

class SecondActivity : AppCompatActivity() {

    lateinit var binding: ActivitySecondBinding
    lateinit var netWorkHelper: NetWorkHelper

    lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        netWorkHelper = NetWorkHelper(this)

        if (netWorkHelper.isNetWorkConnected()) {

            binding.tvS.text = "ConnectedS"
            requestQueue = Volley.newRequestQueue(this)
            fetchImageLoad()
            fetchObjectLoad()

        } else {

            binding.tvS.text = "DisconnectedS"

        }

    }

    //obyekt ma'lumotini olib kelish
    private fun fetchObjectLoad() {

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            "http://ip.jsontest.com/",
            null,
            object : Response.Listener<JSONObject> {
                override fun onResponse(response: JSONObject?) {
                    val str = response?.getString("ip")
                    binding.tvS.text = str
                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    binding.tvS.text = error?.message
                }

            }
        )

        requestQueue.add(jsonObjectRequest)

    }

    //rasm olib kelish
    private fun fetchImageLoad() {


        val imageRequest = ImageRequest(
            "https://i.imgur.com/Nwk25LA.jpg",
            { response ->
                binding.imageView.setImageBitmap(response)


            },
            0,
            0,
            ImageView.ScaleType.FIT_XY,
            Bitmap.Config.ARGB_8888
        ) { error ->
            binding.tvS.text = error?.message


        }

        requestQueue.add(imageRequest)

    }
}